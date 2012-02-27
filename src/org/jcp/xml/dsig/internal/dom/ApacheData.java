/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: ApacheData.java,v 1.4 2005/05/10 18:15:31 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.Data;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;

/**
 * XMLSignatureInput Data wrapper. 
 *
 * @author Sean Mullan
 */
public interface ApacheData extends Data {

    /**
     * Returns the XMLSignatureInput.
     */
    public XMLSignatureInput getXMLSignatureInput();
}
