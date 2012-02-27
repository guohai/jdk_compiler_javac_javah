package com.sun.tools.javac.zip;

import java.io.File;
import java.util.Calendar;

public final class ZipFileIndexEntry implements Comparable<ZipFileIndexEntry> {
    public static final ZipFileIndexEntry[] EMPTY_ARRAY = {};
    
    // Directory related
    String dir;
    boolean isDir;
    
    // File related
    String name;
    
    int offset;
    int size;
    int compressedSize;
    long javatime;
    
    private int nativetime;
    
    public ZipFileIndexEntry(String path) {
        int separator = path.lastIndexOf(File.separatorChar);
        if (separator == -1) {
            dir = "".intern();
            name = path;
        } else {
            dir = path.substring(0, separator).intern();
            name = path.substring(separator + 1);
        }
    }
    
    public ZipFileIndexEntry(String directory, String name) {
        this.dir = directory.intern();
        this.name = name;
    }
    
    public String getName() {
        if (dir == null || dir.length() == 0) {
            return name;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(dir);
        sb.append(File.separatorChar);
        sb.append(name);
        return sb.toString();
    }
   
    public String getFileName() {
        return name;
    }
    
    public long getLastModified() {
        if (javatime == 0) {
                javatime = dosToJavaTime(nativetime);
        }
        return javatime;
    }
  
    // based on dosToJavaTime in java.util.Zip, but avoiding the
    // use of deprecated Date constructor
    private static long dosToJavaTime(int dtime) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,        ((dtime >> 25) & 0x7f) + 1980);
        c.set(Calendar.MONTH,       ((dtime >> 21) & 0x0f) - 1);
        c.set(Calendar.DATE,        ((dtime >> 16) & 0x1f));
        c.set(Calendar.HOUR_OF_DAY, ((dtime >> 11) & 0x1f));
        c.set(Calendar.MINUTE,      ((dtime >>  5) & 0x3f));
        c.set(Calendar.SECOND,      ((dtime <<  1) & 0x3e));
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }
    
    void setNativeTime(int natTime) {
        nativetime = natTime;
    }
    
    public boolean isDirectory() {
        return isDir;
    }
    
    public int compareTo(ZipFileIndexEntry other) {
        String otherD = other.dir;
        if (dir != otherD) {
            int c = dir.compareTo(otherD);
            if (c != 0)
                return c;
        }
        return name.compareTo(other.name);
    }
    
    
    public String toString() {
        return isDir ? ("Dir:" + dir + " : " + name) :
            (dir + ":" + name);
    }
}
