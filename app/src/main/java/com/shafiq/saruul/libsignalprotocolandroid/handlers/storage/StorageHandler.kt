package com.shafiq.saruul.libsignalprotocolandroid.handlers.storage

interface StorageHandler {

    fun hasFile(filePath: FilePath, fileName: String): Boolean

    fun deleteFile(filePath: FilePath, fileName: String): Boolean

    fun transformToJsonAndSaveToFile(filePath: FilePath, fileName: String, data: Any?): Boolean

    fun readFile(filePath: FilePath, fileName: String): String?
}
