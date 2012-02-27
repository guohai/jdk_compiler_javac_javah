/**
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://wwws.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.model;

import javax.tools.JavaFileObject;
import com.sun.tools.javac.util.Position;
import com.sun.tools.javac.util.Version;

/**
 * Implementation of model API SourcePosition based on javac internal state.
 *
 * <p><b>This is NOT part of any API supported by Sun Microsystems.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
@Version("%W% %E%")
class JavacSourcePosition {

    final JavaFileObject sourcefile;
    final int pos;
    final Position.LineMap lineMap;

    JavacSourcePosition(JavaFileObject sourcefile,
			int pos,
			Position.LineMap lineMap) {
	this.sourcefile = sourcefile;
	this.pos = pos;
	this.lineMap = (pos != Position.NOPOS) ? lineMap : null;
    }

    public JavaFileObject getFile() {
	return sourcefile;
    }

    public int getOffset() {
	return pos;	// makes use of fact that Position.NOPOS == -1
    }

    public int getLine() {
	return (lineMap != null) ? lineMap.getLineNumber(pos) : -1;
    }

    public int getColumn() {
	return (lineMap != null) ? lineMap.getColumnNumber(pos) : -1;
    }

    public String toString() {
	int line = getLine();
	return (line > 0)
	      ? sourcefile + ":" + line
	      : sourcefile.toString();
    }
}
