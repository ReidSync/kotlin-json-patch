package com.alightcreative.util.jsonpatch.utils

object IOUtils {
    //see http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
//    fun toString(`is`: java.io.InputStream?, charsetName: String?): String {
//        val s: java.util.Scanner = java.util.Scanner(`is`, charsetName).useDelimiter("\\A")
//        return if (s.hasNext()) s.next() else ""
//    }
    fun toString(fileName: String): String {
        return fileName
    }
}