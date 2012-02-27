/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: ApacheOctetStreamData.java,v 1.4 2005/05/10 18:15:31 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import java.io.IOException;
import javax.xml.crypto.OctetStreamData;
import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;

public class ApacheOctetStreamData extends OctetStreamData 
    implements ApacheData {

    private XMLSignatureInput xi;

    public ApacheOctetStreamData(XMLSignatureInput xi) 
	throws CanonicalizationException, IOException {
	super(xi.getOctetStream(), xi.getSourceURI(), xi.getMIMEType());
        this.xi = xi;
    }

    public XMLSignatureInput getXMLSignatureInput() {
        return xi;
    }
}
