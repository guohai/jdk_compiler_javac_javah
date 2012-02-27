/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: DOMCryptoBinary.java,v 1.14 2005/05/12 19:28:29 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import java.math.BigInteger;
import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.*;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * A DOM-based representation of the XML <code>CryptoBinary</code> simple type 
 * as defined in the W3C specification for XML-Signature Syntax and Processing.
 * The XML Schema Definition is defined as:
 *
 * <xmp>
 * <simpleType name="CryptoBinary">
 *   <restriction base = "base64Binary">
 *   </restriction>
 * </simpleType>
 * </xmp>
 * 
 * @author Sean Mullan
 */
public final class DOMCryptoBinary extends DOMStructure {

    private final BigInteger bigNum;
    private final String value;

    /**
     * Create a <code>DOMCryptoBinary</code> instance from the specified
     * <code>BigInteger</code>
     *
     * @param bigNum the arbitrary-length integer
     * @throws NullPointerException if <code>bigNum</code> is <code>null</code>
     */
    public DOMCryptoBinary(BigInteger bigNum) {
        if (bigNum == null) {
            throw new NullPointerException("bigNum is null");
        }
        this.bigNum = bigNum;
        // convert to bitstring
        value = Base64.encode(bigNum);
    }

    /**
     * Creates a <code>DOMCryptoBinary</code> from a node.
     *
     * @param cbNode a CryptoBinary text node
     * @throws MarshalException if value cannot be decoded (invalid format)
     */
    public DOMCryptoBinary(Node cbNode) throws MarshalException {
        value = cbNode.getNodeValue();
        try {
            bigNum = Base64.decodeBigIntegerFromText((Text) cbNode);
        } catch (Exception ex) {
            throw new MarshalException(ex);
        }
    }

    /**
     * Returns the <code>BigInteger</code> that this object contains.
     *
     * @return the <code>BigInteger</code> that this object contains
     */
    public BigInteger getBigNum() {
	return bigNum;
    }

    public void marshal(Node parent, String prefix, DOMCryptoContext context) 
	throws MarshalException {
        parent.appendChild
	    (DOMUtils.getOwnerDocument(parent).createTextNode(value));
    }
}
