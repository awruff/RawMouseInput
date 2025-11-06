package com.github.awruff.rawinput.utils

import java.io.File

object LibraryChecker {
    fun isLibraryPresent(name: String): Boolean {
        val libraryPath = System.getProperty("java.library.path") ?: return false
        val mappedName = System.mapLibraryName(name)

        return runCatching {
            libraryPath.split(File.pathSeparator)
                .any { File(it, mappedName).exists() }
        }.getOrDefault(false)
    }
}
