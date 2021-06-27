package com.shafiq.saruul.libsignalprotocolandroid.handlers.storage

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import java.io.File
import javax.inject.Inject

class ExampleStorageHandler @Inject constructor(
    private val context: Context,
    private val gson: Gson
) : StorageHandler {

    override fun hasFile(filePath: FilePath, fileName: String): Boolean {
        return File(filePath.filePath, fileName).exists()
    }

    override fun deleteFile(filePath: FilePath, fileName: String): Boolean {
        if (hasFile(filePath, fileName)) {
            return File(filePath.filePath, fileName).delete()
        }
        return true
    }

    private fun getEncryptedFile(filePath: FilePath, fileName: String): EncryptedFile {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        val directory = File(filePath.filePath)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return EncryptedFile.Builder(
            File(filePath.filePath, fileName),
            context,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
    }

    private fun saveToFile(filePath: FilePath, fileName: String, data: String): Boolean {
        deleteFile(filePath, fileName)
        getEncryptedFile(filePath, fileName).openFileOutput().bufferedWriter().use {
            it.write(data)
            it.close()
        }
        return true
    }

    override fun transformToJsonAndSaveToFile(
        filePath: FilePath,
        fileName: String,
        data: Any?
    ): Boolean {
        return saveToFile(filePath, fileName, gson.toJson(data))
    }

    override fun readFile(filePath: FilePath, fileName: String): String? {
        if (hasFile(filePath, fileName)) {
            getEncryptedFile(filePath, fileName).openFileInput().bufferedReader()
                .use { bufferedReader ->
                    var line = bufferedReader.readLine()
                    val stringBuilder = StringBuilder()
                    while (line != null) {
                        stringBuilder.append(line)
                        line = bufferedReader.readLine()
                    }
                    bufferedReader.close()
                    return stringBuilder.toString()
                }
        }
        return null
    }
}
