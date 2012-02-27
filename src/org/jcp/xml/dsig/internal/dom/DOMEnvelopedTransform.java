/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMEnvelopedTransform.java,v 1.16 2005/05/10 18:15:32 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import java.security.InvalidAlgorithmParameterException;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

/**
 * DOM-based implementation of Enveloped Signature Transform.
 * (Uses Apache XML-Sec Transform implementation)
 *
 * @author Sean Mullan
 */
public final class DOMEnvelopedTransform extends ApacheTransform {

    public void init(TransformParameterSpec params)
        throws InvalidAlgorithmParameterException {
        if (params != null) {
	    throw new InvalidAlgorithmParameterException("params must be null");
	}
    }
}
