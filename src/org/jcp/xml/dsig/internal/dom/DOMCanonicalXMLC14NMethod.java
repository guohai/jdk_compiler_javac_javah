/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMCanonicalXMLC14NMethod.java,v 1.24.4.1 2005/08/12 15:27:49 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import java.security.InvalidAlgorithmParameterException;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;

/**
 * DOM-based implementation of CanonicalizationMethod for Canonical XML
 * (with or without comments). Uses Apache XML-Sec Canonicalizer.
 *
 * @author Sean Mullan
 */
public final class DOMCanonicalXMLC14NMethod extends ApacheCanonicalizer {

    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException {
        if (params != null) {
            throw new InvalidAlgorithmParameterException("no parameters " +
                "should be specified for Canonical XML C14N algorithm");
        }
    }

    public Data transform(Data data, XMLCryptoContext xc)
	throws TransformException {

        // ignore comments if dereferencing same-document URI that requires
	// you to omit comments, even if the Transform says otherwise -
        // this is to be compliant with section 4.3.3.3 of W3C Rec.
	if (data instanceof DOMSubTreeData) {
	    DOMSubTreeData subTree = (DOMSubTreeData) data;
	    if (subTree.excludeComments()) {
                try {
                    apacheCanonicalizer = Canonicalizer.getInstance
			(CanonicalizationMethod.INCLUSIVE);
                } catch (InvalidCanonicalizerException ice) {
		    throw new TransformException
                        ("Couldn't find Canonicalizer for: " +
		         CanonicalizationMethod.INCLUSIVE + ": " +
		         ice.getMessage(), ice);
                }
	    }
	}

	return canonicalize(data, xc);
    }
}
