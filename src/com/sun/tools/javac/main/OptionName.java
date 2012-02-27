/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://www.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac.main;

import com.sun.tools.javac.util.Version;

/**
 * TODO: describe com.sun.tools.javac.main.OptionName
 *
 * <p><b>This is NOT part of any API supported by Sun Microsystems.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
@Version("%W% %E%")
public enum OptionName {
    G("-g"),
    G_NONE("-g:none"),
    G_CUSTOM("-g:{lines,vars,source}"),
    XLINT("-Xlint"),
    XLINT_CUSTOM("-Xlint:{"
                 + "all,"
                 + "cast,deprecation,divzero,empty,unchecked,fallthrough,path,serial,finally,overrides,"
                 + "-cast,-deprecation,-divzero,-empty,-unchecked,-fallthrough,-path,-serial,-finally,-overrides,"
                 + "none}"),
    NOWARN("-nowarn"),
    VERBOSE("-verbose"),
    DEPRECATION("-deprecation"),
    CLASSPATH("-classpath"),
    CP("-cp"),
    SOURCEPATH("-sourcepath"),
    BOOTCLASSPATH("-bootclasspath"),
    XBOOTCLASSPATH_PREPEND("-Xbootclasspath/p:"),
    XBOOTCLASSPATH_APPEND("-Xbootclasspath/a:"),
    XBOOTCLASSPATH("-Xbootclasspath:"),
    EXTDIRS("-extdirs"),
    DJAVA_EXT_DIRS("-Djava.ext.dirs="),
    ENDORSEDDIRS("-endorseddirs"),
    DJAVA_ENDORSED_DIRS("-Djava.endorsed.dirs="),
    PROC_CUSTOM("-proc:{none,only}"),
    PROCESSOR("-processor"),
    PROCESSORPATH("-processorpath"),
    D("-d"),
    S("-s"),
    IMPLICIT("-implicit:{none,class}"),
    ENCODING("-encoding"),
    SOURCE("-source"),
    TARGET("-target"),
    VERSION("-version"),
    FULLVERSION("-fullversion"),
    HELP("-help"),
    A("-A"),
    X("-X"),
    J("-J"),
    MOREINFO("-moreinfo"),
    WERROR("-Werror"),
    COMPLEXINFERENCE("-complexinference"),
    PROMPT("-prompt"),
    DOE("-doe"),
    PRINTSOURCE("-printsource"),
    WARNUNCHECKED("-warnunchecked"),
    XMAXERRS("-Xmaxerrs"),
    XMAXWARNS("-Xmaxwarns"),
    XSTDOUT("-Xstdout"),
    XPRINT("-Xprint"),
    XPRINTROUNDS("-XprintRounds"),
    XPRINTPROCESSORINFO("-XprintProcessorInfo"),
    XPREFER("-Xprefer:{source,newer}"),
    O("-O"),
    XJCOV("-Xjcov"),
    XD("-XD"),
    SOURCEFILE("sourcefile");

    public final String optionName;

    OptionName(String optionName) {
        this.optionName = optionName;
    }

    @Override
    public String toString() {
        return optionName;
    }

}
