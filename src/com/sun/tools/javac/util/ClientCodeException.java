/**
 * %W% %E%
 *
 * Copyright (c) 2005, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://wwws.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.util;

/**
 * An exception used for propogating exceptions found in client code
 * invoked from javac.
 *
 *  <p><b>This is NOT part of any API supported by Sun Microsystems.  If
 *  you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
@Version("%W% %E%")
public class ClientCodeException extends RuntimeException {

    static final long serialVersionUID = -5674494409392415163L;

    public ClientCodeException(Throwable cause) {
	super(cause);
    }
}
