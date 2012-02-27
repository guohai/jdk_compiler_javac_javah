/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMManifest.java,v 1.16 2005/05/12 19:28:31 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.*;

import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-based implementation of Manifest.
 *
 * @author Sean Mullan
 */
public final class DOMManifest extends DOMStructure implements Manifest {

    private final List references;
    private final String id;

    /**
     * Creates a <code>DOMManifest</code> containing the specified
     * list of {@link Reference}s and optional id.
     *
     * @param references a list of one or more <code>Reference</code>s. The list
     *    is defensively copied to protect against subsequent modification.
     * @param id the id (may be <code>null</code>
     * @throws NullPointerException if <code>references</code> is
     *    <code>null</code>
     * @throws IllegalArgumentException if <code>references</code> is empty
     * @throws ClassCastException if <code>references</code> contains any
     *    entries that are not of type {@link Reference}
     */
    public DOMManifest(List references, String id) {
	if (references == null) {
	    throw new NullPointerException("references cannot be null");
	}
	List refCopy = new ArrayList(references);
	if (refCopy.isEmpty()) {
	    throw new IllegalArgumentException("list of references must " +
	        "contain at least one entry");
	}
        for (int i = 0, size = refCopy.size(); i < size; i++) {
            if (!(refCopy.get(i) instanceof Reference)) {
                throw new ClassCastException
                    ("references["+i+"] is not a valid type");
            }
        }
	this.references = Collections.unmodifiableList(refCopy);
	this.id = id;
    }

    /**
     * Creates a <code>DOMManifest</code> from an element.
     *
     * @param manElem a Manifest element
     */
    public DOMManifest(Element manElem, XMLCryptoContext context) 
	throws MarshalException {
        this.id = DOMUtils.getAttributeValue(manElem, "Id");
        Element refElem = DOMUtils.getFirstChildElement(manElem);
	List refs = new ArrayList();
	while (refElem != null) {
	    refs.add(new DOMReference(refElem, context));
	    refElem = DOMUtils.getNextSiblingElement(refElem);
	}
	this.references = Collections.unmodifiableList(refs);
    }

    public String getId() {
	return id;
    }
    
    public List getReferences() {
        return references;
    }

    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context)
	throws MarshalException {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);

        Element manElem = DOMUtils.createElement
            (ownerDoc, "Manifest", XMLSignature.XMLNS, dsPrefix);

        DOMUtils.setAttributeID(manElem, "Id", id);

	// add references
	for (int i = 0, size = references.size(); i < size; i++) {
	    DOMReference ref = (DOMReference) references.get(i);
	    ref.marshal(manElem, dsPrefix, context);
	}
        parent.appendChild(manElem);
    }

    public boolean equals(Object o) {
	if (this == o) {
            return true;
	}

	if (!(o instanceof Manifest)) {
            return false;
	}
        Manifest oman = (Manifest) o;

	boolean idsEqual = (id == null ? oman.getId() == null :
            id.equals(oman.getId()));

	return (idsEqual && references.equals(oman.getReferences()));
    }
}
