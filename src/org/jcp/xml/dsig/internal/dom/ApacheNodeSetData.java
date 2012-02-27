/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: ApacheNodeSetData.java,v 1.4 2005/05/10 18:15:31 mullan Exp $
 */
package org.jcp.xml.dsig.internal.dom;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.xml.crypto.NodeSetData;
import org.w3c.dom.Node;
import com.sun.org.apache.xml.internal.security.signature.NodeFilter;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;

public class ApacheNodeSetData implements ApacheData, NodeSetData {

    private XMLSignatureInput xi;

    public ApacheNodeSetData(XMLSignatureInput xi) {
        this.xi = xi;
    }

    public Iterator iterator() {
        // If nodefilters are set, must execute them first to create node-set
        if (xi.getNodeFilters() != null) {
            return Collections.unmodifiableSet
                (getNodeSet(xi.getNodeFilters())).iterator();
        }
        try {
            return Collections.unmodifiableSet(xi.getNodeSet()).iterator();
        } catch (Exception e) {
            // should not occur
            throw new RuntimeException
                ("unrecoverable error retrieving nodeset", e);
        }
    }

    public XMLSignatureInput getXMLSignatureInput() {
        return xi;
    }

    private Set getNodeSet(List nodeFilters) {
        if (xi.isNeedsToBeExpanded()) {
            XMLUtils.circumventBug2650
                (XMLUtils.getOwnerDocument(xi.getSubNode()));
        }

        Set inputSet = new LinkedHashSet();
        XMLUtils.getSet
          (xi.getSubNode(), inputSet, null, !xi.isExcludeComments());
        Set nodeSet = new LinkedHashSet();
        Iterator i = inputSet.iterator();
        while (i.hasNext()) {
            Node currentNode = (Node) i.next();
            Iterator it = nodeFilters.iterator();
            boolean skipNode = false;
            while (it.hasNext() && !skipNode) {
                NodeFilter nf = (NodeFilter) it.next();
                if (!nf.isNodeInclude(currentNode)) {
                    skipNode = true;
                }
            }
            if (!skipNode) {
                nodeSet.add(currentNode);
            }
        }
        return nodeSet;
    }
}
