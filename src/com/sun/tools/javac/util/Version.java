/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.tools.javac.util;

import java.lang.annotation.*;

/**
 * Used to provide version info for a class.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@Version("%W% %E%")
public @interface Version {
    String value();
}
