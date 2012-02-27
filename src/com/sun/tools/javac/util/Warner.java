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

import com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition;

/**
 * An interface to support optional warnings, needed for support of
 * unchecked conversions and unchecked casts.
 *
 * <p>Nothing described in this source file is part of any supported
 * API.  If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.
 */
@Version("%W% %E%")
public class Warner {
    public static final Warner noWarnings = new Warner();

    private DiagnosticPosition pos = null;
    public boolean warned = false;
    public boolean unchecked = false;

    public DiagnosticPosition pos() {
        return pos;
    }

    public void warnUnchecked() {
        warned = true;
        unchecked = true;
    }
    public void silentUnchecked() {
        unchecked = true;
    }

    public Warner(DiagnosticPosition pos) {
        this.pos = pos;
    }

    public Warner() {
        this(null);
    }
}
