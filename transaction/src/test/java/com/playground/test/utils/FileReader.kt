package com.playground.test.utils

import java.io.FileNotFoundException
import java.net.URL

object FileReader {

    /**
     * Helper function which will read a file and return it`s content as text.
     *
     * The [path] is the relative path of the file with its extension.
     * A [FileNotFoundException] is thrown if content is null or path does not exist.
     */
    fun read(path: String): String {
        val content: URL? = ClassLoader.getSystemResource(path)
        return content?.readText() ?: throw FileNotFoundException("File path: $path was not found.")
    }
}
