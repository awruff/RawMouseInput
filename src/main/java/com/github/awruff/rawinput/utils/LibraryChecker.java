package com.github.awruff.rawinput.utils;

import java.io.File;

public class LibraryChecker {
    public static boolean isLibraryLoaded(String name) {
        try {
            String path = System.getProperty("java.library.path");
            if (path != null) {
                String mapped = System.mapLibraryName(name);
                String[] paths = path.split(File.pathSeparator != null ? File.pathSeparator : ";");
                for (String libPath : paths) {
                    if (new File(libPath, mapped).exists()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}