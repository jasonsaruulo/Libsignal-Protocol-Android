package com.shafiq.saruul.libsignalprotocolandroid.storage

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import java.io.File
import javax.inject.Inject

class ExampleStorageHandler @Inject constructor(private val gson: Gson) : StorageHandler {

    private fun hasFile(filePath: String, fileName: String): Boolean {
        return File(filePath, fileName).exists()
    }

    private fun deleteFile(filePath: String, fileName: String): Boolean {
        if (hasFile(filePath, fileName)) {
            return File(filePath, fileName).delete()
        }
        return true
    }

    private fun getEncryptedFile(
        context: Context,
        filePath: String,
        fileName: String
    ): EncryptedFile {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        val directory = File(filePath)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return EncryptedFile.Builder(
            File(filePath, fileName),
            context,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
    }

    private fun saveToFile(
        context: Context,
        filePath: String,
        fileName: String,
        data: String
    ): Boolean {
        deleteFile(filePath, fileName)
        getEncryptedFile(context, filePath, fileName).openFileOutput().bufferedWriter().use {
            it.write(data)
            it.close()
        }
        return true
    }

    override fun transformToJsonAndSaveToFile(
        context: Context, filePath: FilePath, fileName: String, data: Any?
    ): Boolean {
        return saveToFile(context, filePath.filePath, fileName, gson.toJson(data))
    }
}
