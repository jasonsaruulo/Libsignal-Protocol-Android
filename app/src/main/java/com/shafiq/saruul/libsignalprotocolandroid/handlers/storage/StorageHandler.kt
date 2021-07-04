package com.shafiq.saruul.libsignalprotocolandroid.handlers.storage

interface StorageHandler {

    fun hasFile(filePath: FilePath, fileName: String): Boolean

    fun listFileNames(filePath: FilePath): List<String>

    fun deleteFile(filePath: FilePath, fileName: String): Boolean

    fun deleteDirectory(filePath: FilePath)

    fun transformToJsonAndSaveToFile(filePath: FilePath, fileName: String, data: Any?): Boolean

    fun readFile(filePath: FilePath, fileName: String): String?
}
