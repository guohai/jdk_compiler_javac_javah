/*
 * ===========================================================================
 *
 * (C) Copyright IBM Corp. 2003 All Rights Reserved.
 *
 * ===========================================================================
 */
/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: XMLDSigRI.java,v 1.7 2005/05/12 19:28:36 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import java.util.*;
import java.security.*;

import javax.xml.crypto.dsig.*;

/**
 * The XMLDSig RI Provider.
 *
 * @author Joyce Leung
 */

/**
 * Defines the XMLDSigRI provider.
 */

public final class XMLDSigRI extends Provider {

    static final long serialVersionUID = -5049765099299494554L;

    private static final String INFO = "XMLDSig " + 
    "(DOM XMLSignatureFactory; DOM KeyInfoFactory)";

    public XMLDSigRI() {
	/* We are the XMLDSig provider */
	super("XMLDSig", 1.0, INFO);
	
	final Map map = new HashMap();
        map.put("XMLSignatureFactory.DOM", 
	        "org.jcp.xml.dsig.internal.dom.DOMXMLSignatureFactory");
        map.put("KeyInfoFactory.DOM", 
	        "org.jcp.xml.dsig.internal.dom.DOMKeyInfoFactory");


	// Inclusive C14N
	map.put((String)"TransformService." + CanonicalizationMethod.INCLUSIVE,
		"org.jcp.xml.dsig.internal.dom.DOMCanonicalXMLC14NMethod");
	map.put("Alg.Alias.TransformService.INCLUSIVE", 
		CanonicalizationMethod.INCLUSIVE);
	map.put((String)"TransformService." + CanonicalizationMethod.INCLUSIVE +
		" MechanismType", "DOM");

	// InclusiveWithComments C14N
	map.put((String) "TransformService." + 
		CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS,
		"org.jcp.xml.dsig.internal.dom.DOMCanonicalXMLC14NMethod");
	map.put("Alg.Alias.TransformService.INCLUSIVE_WITH_COMMENTS", 
		CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS);
	map.put((String) "TransformService." + 
		CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS + 
		" MechanismType", "DOM");

	// Exclusive C14N
	map.put((String) "TransformService." + CanonicalizationMethod.EXCLUSIVE,
		"org.jcp.xml.dsig.internal.dom.DOMExcC14NMethod");
	map.put("Alg.Alias.TransformService.EXCLUSIVE", 
		CanonicalizationMethod.EXCLUSIVE);
	map.put((String)"TransformService." + CanonicalizationMethod.EXCLUSIVE +
		" MechanismType", "DOM");

	// ExclusiveWithComments C14N
	map.put((String) "TransformService." + 
		CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS,
		"org.jcp.xml.dsig.internal.dom.DOMExcC14NMethod");
	map.put("Alg.Alias.TransformService.EXCLUSIVE_WITH_COMMENTS", 
		CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS);
	map.put((String) "TransformService." + 
		CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS +
		" MechanismType", "DOM");

	// Base64 Transform
	map.put((String) "TransformService." + Transform.BASE64,
		"org.jcp.xml.dsig.internal.dom.DOMBase64Transform");
	map.put("Alg.Alias.TransformService.BASE64", Transform.BASE64);
	map.put((String) "TransformService." + Transform.BASE64 + 
		" MechanismType", "DOM");

	// Enveloped Transform
	map.put((String) "TransformService." + Transform.ENVELOPED,
		"org.jcp.xml.dsig.internal.dom.DOMEnvelopedTransform");
	map.put("Alg.Alias.TransformService.ENVELOPED", Transform.ENVELOPED);
	map.put((String) "TransformService." + Transform.ENVELOPED + 
		" MechanismType", "DOM");

	// XPath2 Transform
	map.put((String) "TransformService." + Transform.XPATH2,
		"org.jcp.xml.dsig.internal.dom.DOMXPathFilter2Transform");
	map.put("Alg.Alias.TransformService.XPATH2", Transform.XPATH2);
	map.put((String) "TransformService." + Transform.XPATH2 + 
		" MechanismType", "DOM");

	// XPath Transform
	map.put((String) "TransformService." + Transform.XPATH,
		"org.jcp.xml.dsig.internal.dom.DOMXPathTransform");
	map.put("Alg.Alias.TransformService.XPATH", Transform.XPATH);
	map.put((String) "TransformService." + Transform.XPATH + 
		" MechanismType", "DOM");

	// XSLT Transform
	map.put((String) "TransformService." + Transform.XSLT,
		"org.jcp.xml.dsig.internal.dom.DOMXSLTTransform");
	map.put("Alg.Alias.TransformService.XSLT", Transform.XSLT);
	map.put((String) "TransformService." + Transform.XSLT + 
		" MechanismType", "DOM");

	AccessController.doPrivileged(new java.security.PrivilegedAction() {
	    public Object run() {
		putAll(map);
		return null;
	    }
	});
    }
}
