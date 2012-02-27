/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://www.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.tools.*;

import static javax.tools.StandardLocation.SOURCE_PATH;

/**
 * Provides an easy migration to JSR 199 v3.3.  The class is
 * deprecated as we should remove it as soon as possible.
 *
 * <p><b>This is NOT part of any API supported by Sun Microsystems.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 *
 * @author Peter von der Ah\u00e9
 */
@Deprecated
@Version("%W% %E%")
public class Old199 {

    private Old199() {}
    
    public static String getPath(FileObject jfo) {
	return DefaultFileManager.getJavacFileName(jfo);
    }

    public static String getName(FileObject jfo) {
	return DefaultFileManager.getJavacBaseFileName(jfo);
    }

}
