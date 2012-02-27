/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMStructure.java,v 1.11 2005/05/10 18:15:34 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMCryptoContext;
import org.w3c.dom.Node;

/**
 * DOM-based abstract implementation of XMLStructure.
 *
 * @author Sean Mullan
 */
public abstract class DOMStructure implements XMLStructure {

    public final boolean isFeatureSupported(String feature) {
	if (feature == null) {
	    throw new NullPointerException();
	} else {
	    return false;
	}
    }

    public abstract void marshal(Node parent, String dsPrefix, 
	DOMCryptoContext context) throws MarshalException;
}
