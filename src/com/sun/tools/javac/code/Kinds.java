/**
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://wwws.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.code;

import com.sun.tools.javac.util.Version;

/** Internal symbol kinds, which distinguish between elements of
 *  different subclasses of Symbol. Symbol kinds are organized so they can be
 *  or'ed to sets.
 *
 *  <p><b>This is NOT part of any API supported by Sun Microsystems.  If
 *  you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
@Version("%W% %E%")
public class Kinds {

    private Kinds() {} // uninstantiable

    /** The empty set of kinds.
     */
    public final static int NIL = 0;

    /** The kind of package symbols.
     */
    public final static int PCK = 1 << 0;

    /** The kind of type symbols (classes, interfaces and type variables).
     */
    public final static int TYP = 1 << 1;

    /** The kind of variable symbols.
     */
    public final static int VAR = 1 << 2;

    /** The kind of values (variables or non-variable expressions), includes VAR.
     */
    public final static int VAL = (1 << 3) | VAR;

    /** The kind of methods.
     */
    public final static int MTH = 1 << 4;

    /** The error kind, which includes all other kinds.
     */
    public final static int ERR = (1 << 5) - 1;

    /** The set of all kinds.
     */
    public final static int AllKinds = ERR;

    /** Kinds for erroneous symbols that complement the above
     */
    public static final int ERRONEOUS = 1 << 6;
    public static final int AMBIGUOUS    = ERRONEOUS+1; // ambiguous reference
    public static final int HIDDEN       = ERRONEOUS+2; // hidden method or field
    public static final int STATICERR    = ERRONEOUS+3; // nonstatic member from static context
    public static final int ABSENT_VAR   = ERRONEOUS+4; // missing variable
    public static final int WRONG_MTHS   = ERRONEOUS+5; // methods with wrong arguments
    public static final int WRONG_MTH    = ERRONEOUS+6; // one method with wrong arguments
    public static final int ABSENT_MTH   = ERRONEOUS+7; // missing method
    public static final int ABSENT_TYP   = ERRONEOUS+8; // missing type
}
