/*
 * Copyright (c) 2006, 2008, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMURIDereferencer.java,v 1.19 2005/09/23 20:09:34 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.apache.xml.internal.security.Init;
import com.sun.org.apache.xml.internal.security.utils.IdResolver;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolver;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;

import javax.xml.crypto.*;
import javax.xml.crypto.dom.*;
import javax.xml.crypto.dsig.*;

/**
 * DOM-based implementation of URIDereferencer.
 *
 * @author Sean Mullan
 */
public class DOMURIDereferencer implements URIDereferencer {
    
    static final URIDereferencer INSTANCE = new DOMURIDereferencer();

    private DOMURIDereferencer() {
	// need to call com.sun.org.apache.xml.internal.security.Init.init() 
        // before calling any apache security code
	Init.init();
    }

    public Data dereference(URIReference uriRef, XMLCryptoContext context)
	throws URIReferenceException {

	if (uriRef == null) {
	    throw new NullPointerException("uriRef cannot be null");
	}
	if (context == null) {
	    throw new NullPointerException("context cannot be null");
	}

	DOMURIReference domRef = (DOMURIReference) uriRef;
        Attr uriAttr = (Attr) domRef.getHere();
	String uri = uriRef.getURI();
        DOMCryptoContext dcc = (DOMCryptoContext) context;

	// Check if same-document URI and register ID
	if (uri != null && uri.length() != 0 && uri.charAt(0) == '#') {
            String id = uri.substring(1);

	    if (id.startsWith("xpointer(id(")) {
	        int i1 = id.indexOf('\'');
	        int i2 = id.indexOf('\'', i1+1);
		id = id.substring(i1+1, i2);
	    }

            // Check for registered IDRefs and manually register them with 
	    // Apache's IdResolver map which includes builtin schema knowledge 
	    // of DSig/Enc IDs
            Node referencedElem = dcc.getElementById(id);
	    if (referencedElem != null) {
	        IdResolver.registerElementById((Element) referencedElem, id);
	    }
	} 

        try {
	    String baseURI = context.getBaseURI();
            ResourceResolver apacheResolver = 
	        ResourceResolver.getInstance(uriAttr, baseURI);
            XMLSignatureInput in = apacheResolver.resolve(uriAttr, baseURI);
	    if (in.isOctetStream()) {
	        return new ApacheOctetStreamData(in);
	    } else {
	        return new ApacheNodeSetData(in);
	    }
        } catch (Exception e) {
            throw new URIReferenceException(e);
        }
    }
}
