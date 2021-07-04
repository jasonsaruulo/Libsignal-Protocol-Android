package com.shafiq.saruul.libsignalprotocolandroid.handlers.storage

class FilePath constructor(homeDirectory: String, vararg identifiers: String) {

    val filePath: String

    init {
        var filePath = homeDirectory
        for (identifier in identifiers) {
            filePath += "/"
            filePath += identifier
        }
        this.filePath = filePath
    }

    fun suffix(folder: String): FilePath {
        return FilePath(filePath, folder)
    }
}
