/**
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Use and Distribution is subject to the Java Research License available
 * at <http://wwws.sun.com/software/communitysource/jrl.html>.
 */

package com.sun.tools.javac;

import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.sun.tools.javac.util.Version;

/**
 * <b>Unsupported</b> entry point for starting javac from an IDE.
 *
 * <p><b>Note:</b> this class is not available in the JDK.  It is not
 * compiled by default and will not be in tools.jar.  It is designed
 * to be useful when editing the compiler sources in an IDE (as part
 * of a <em>project</em>).  Simply ensure that this class is added to
 * the project and make it the main class of the project.</p>
 *
 * <p><b>This is NOT part of any API supported by Sun Microsystems.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 *
 * @author Peter von der Ah&eacute;
 * @since 1.6
 */
@Version("%W% %E%")
class Launcher {
    public static void main(String... args) {
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        JFileChooser fileChooser;
        Preferences prefs = Preferences.userNodeForPackage(Launcher.class);
        if (args.length > 0)
            fileChooser = new JFileChooser(args[0]);
        else {
            String fileName = prefs.get("recent.file", null);
            fileChooser = new JFileChooser();
            if (fileName != null) {
                fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(fileName));
            }
        }
        if (fileChooser.showOpenDialog(null) == fileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getPath();
            prefs.put("recent.file", fileName);
            javac.run(System.in, null, null, "-d", "/tmp", fileName);
        }
    }
}
