/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMXSLTTransform.java,v 1.15 2005/05/10 18:15:36 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import java.security.InvalidAlgorithmParameterException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

/**
 * DOM-based implementation of XSLT Transform.
 * (Uses Apache XML-Sec Transform implementation)
 *
 * @author Sean Mullan
 */
public final class DOMXSLTTransform extends ApacheTransform {
 
    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException {
	if (params == null) {
	    throw new InvalidAlgorithmParameterException("params are required");
	}
	if (!(params instanceof XSLTTransformParameterSpec)) {
	    throw new InvalidAlgorithmParameterException("unrecognized params");
	}
	this.params = params;
    }

    public void init(XMLStructure parent, XMLCryptoContext context)
        throws InvalidAlgorithmParameterException {

	super.init(parent, context);
	unmarshalParams(DOMUtils.getFirstChildElement(transformElem));
    }

    private void unmarshalParams(Element sheet) {
	this.params = new XSLTTransformParameterSpec
	    (new javax.xml.crypto.dom.DOMStructure(sheet));
    }

    public void marshalParams(XMLStructure parent, XMLCryptoContext context)
        throws MarshalException {
	super.marshalParams(parent, context);
	XSLTTransformParameterSpec xp = 
	    (XSLTTransformParameterSpec) getParameterSpec();
	Node xsltElem = 
	    ((javax.xml.crypto.dom.DOMStructure) xp.getStylesheet()).getNode();
	DOMUtils.appendChild(transformElem, xsltElem);
    }
}
