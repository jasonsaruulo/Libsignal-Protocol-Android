package com.shafiq.saruul.libsignalprotocolandroid.handlers.storage

import android.content.Context

class FilePath constructor(context: Context, vararg identifiers: String) {

    val filePath: String

    init {
        var filePath = context.filesDir.absolutePath
        for (identifier in identifiers) {
            filePath += "/"
            filePath += identifier
        }
        this.filePath = filePath
    }
}
