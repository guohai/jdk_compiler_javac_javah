/**
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://wwws.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.processing;

import com.sun.tools.javac.util.Version;

/** 
 * Error thrown for problems encountered during annotation processing.
 *
 * <p><b>This is NOT part of any API supported by Sun Microsystems.
 * If you write code that depends on this, you do so at your own risk.
 * This code and its internal interfaces are subject to change or
 * deletion without notice.</b>
 */
@Version("%W% %E%")
public class AnnotationProcessingError extends Error {
    static final long serialVersionUID = 305337707019230790L;
    AnnotationProcessingError(Throwable cause) {
	super(cause);
    }
}
