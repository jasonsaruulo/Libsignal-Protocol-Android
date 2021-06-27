package com.shafiq.saruul.libsignalprotocolandroid.storage

import android.content.Context

interface StorageHandler {

    fun transformToJsonAndSaveToFile(
        context: Context, filePath: FilePath, fileName: String, data: Any?
    ): Boolean
}
