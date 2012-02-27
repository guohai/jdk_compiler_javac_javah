/**
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://wwws.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.util;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;
import static javax.tools.JavaFileObject.Kind.*;

@Version("%W% %E%")
public abstract class BaseFileObject implements JavaFileObject {

    public JavaFileObject.Kind getKind() {
	String n = getName();
	if (n.endsWith(CLASS.extension))
	    return CLASS;
	else if (n.endsWith(SOURCE.extension))
	    return SOURCE;
	else if (n.endsWith(HTML.extension))
	    return HTML;
	else 
	    return OTHER;
    }

    @Override
    public String toString() {
	return getPath();
    }

    /** @deprecated see bug 6410637 */
    @Deprecated
    public String getPath() {
	return getName();
    }

    /** @deprecated see bug 6410637 */
    @Deprecated
    abstract public String getName();

    public NestingKind getNestingKind() { return null; }

    public Modifier getAccessLevel()  { return null; }

}
