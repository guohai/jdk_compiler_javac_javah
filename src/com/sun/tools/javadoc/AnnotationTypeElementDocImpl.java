/**
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.tools.javadoc;

import com.sun.javadoc.*;

import static com.sun.javadoc.LanguageVersion.*;

import com.sun.tools.javac.code.Symbol.*;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Position;

/**
 * Represents an element of an annotation type.
 * 
 * @author Scott Seligman
 * @version %I% %E%
 * @since 1.5
 */

public class AnnotationTypeElementDocImpl
	extends MethodDocImpl implements AnnotationTypeElementDoc {

    AnnotationTypeElementDocImpl(DocEnv env, MethodSymbol sym) {
	super(env, sym);
    }

    AnnotationTypeElementDocImpl(DocEnv env, MethodSymbol sym,
				 String doc, JCMethodDecl tree, Position.LineMap lineMap) {
	super(env, sym, doc, tree, lineMap);
    }

    /**
     * Returns true, as this is an annotation type element.
     * (For legacy doclets, return false.)
     */
    public boolean isAnnotationTypeElement() {
	return !isMethod();
    }

    /**
     * Returns false.  Although this is technically a method, we don't
     * consider it one for this purpose.
     * (For legacy doclets, return true.)
     */
    public boolean isMethod() {
	return env.legacyDoclet;
    }

    /**
     * Returns false, even though this is indeed abstract.  See
     * MethodDocImpl.isAbstract() for the (il)logic behind this.
     */
    public boolean isAbstract() {
	return false;
    }

    /**
     * Returns the default value of this element.
     * Returns null if this element has no default.
     */
    public AnnotationValue defaultValue() {
	return (sym.defaultValue == null)
	       ? null
	       : new AnnotationValueImpl(env, sym.defaultValue);
    }
}
