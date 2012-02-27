/**
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://wwws.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.comp;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Version;


/** {@code Env<A>} specialized as {@code Env<AttrContext>}
 *
 *  <p><b>This is NOT part of any API supported by Sun Microsystems.  If
 *  you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
@Version("%W% %E%")
public class AttrContextEnv extends Env<AttrContext> {

    /** Create an outermost environment for a given (toplevel)tree,
     *  with a given info field.
     */
    public AttrContextEnv(JCTree tree, AttrContext info) {
	super(tree, info);
    }
}
