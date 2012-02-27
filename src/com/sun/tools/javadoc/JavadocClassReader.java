/**
 * @(#)JavadocClassReader.java	1.15 06/03/14
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.tools.javadoc;

import com.sun.tools.javac.code.Symbol.PackageSymbol;
import com.sun.tools.javac.jvm.ClassReader;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.DefaultFileManager;
import com.sun.tools.javac.util.Old199;

import java.io.File;
import java.util.EnumSet;
import javax.tools.JavaFileObject;

/** Javadoc uses an extended class reader that records package.html entries
 *  @author Neal Gafter
 */
class JavadocClassReader extends ClassReader {

    public static JavadocClassReader instance0(Context context) {
	ClassReader instance = context.get(classReaderKey);
	if (instance == null) 
	    instance = new JavadocClassReader(context);
	return (JavadocClassReader)instance;
    }

    public static void preRegister(final Context context) {
	context.put(classReaderKey, new Context.Factory<ClassReader>() {
	    public ClassReader make() {
		return new JavadocClassReader(context);
	    }
	});
    }

    private DocEnv docenv;
    private EnumSet<JavaFileObject.Kind> all = EnumSet.of(JavaFileObject.Kind.CLASS,
							  JavaFileObject.Kind.SOURCE,
							  JavaFileObject.Kind.HTML);
    private EnumSet<JavaFileObject.Kind> noSource = EnumSet.of(JavaFileObject.Kind.CLASS,
							       JavaFileObject.Kind.HTML);

    private JavadocClassReader(Context context) {
	super(context, true);
	docenv = DocEnv.instance(context);
    }

    /**
     * Override getPackageFileKinds to include search for package.html 
     */
    @Override
    protected EnumSet<JavaFileObject.Kind> getPackageFileKinds() {
	return docenv.docClasses ? noSource : all;
    }

    /**
     * Override extraFileActions to check for package documentation
     */
    @Override
    protected void extraFileActions(PackageSymbol pack, JavaFileObject fo) {
	CharSequence fileName = Old199.getName(fo);
	if (docenv != null && fileName.equals("package.html")) {
	    if (fo instanceof DefaultFileManager.ZipFileObject) {
		DefaultFileManager.ZipFileObject zfo = (DefaultFileManager.ZipFileObject) fo;
		String zipName = zfo.getZipName();
		String entryName = zfo.getZipEntryName();
		int lastSep = entryName.lastIndexOf("/");
		String classPathName = entryName.substring(0, lastSep + 1);
		docenv.getPackageDoc(pack).setDocPath(zipName, classPathName);
	    }
            else if (fo instanceof DefaultFileManager.ZipFileIndexFileObject) {
		DefaultFileManager.ZipFileIndexFileObject zfo = (DefaultFileManager.ZipFileIndexFileObject) fo;
		String zipName = zfo.getZipName();
		String entryName = zfo.getZipEntryName();
                if (File.separatorChar != '/') {
                    entryName = entryName.replace(File.separatorChar, '/');
                }
                
		int lastSep = entryName.lastIndexOf("/");
		String classPathName = entryName.substring(0, lastSep + 1);
		docenv.getPackageDoc(pack).setDocPath(zipName, classPathName);
	    }
	    else {
		File fileDir = new File(Old199.getPath(fo)).getParentFile();
		docenv.getPackageDoc(pack).setDocPath(fileDir.getAbsolutePath());
	    }
        }
    }
}
