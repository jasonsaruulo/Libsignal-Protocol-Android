package com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption

import android.content.Context
import com.google.gson.Gson
import com.shafiq.saruul.libsignalprotocolandroid.handlers.HandlerModule.Companion.HOME_DIRECTORY
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.FilePath
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import org.whispersystems.libsignal.InvalidKeyIdException
import org.whispersystems.libsignal.state.PreKeyRecord
import javax.inject.Inject
import javax.inject.Named

class ExamplePreKeyStore @Inject constructor(
    private val gson: Gson,
    @Named(HOME_DIRECTORY) private val homeDirectory: String,
    private val storageHandler: StorageHandler
) : PreKeyStore {

    companion object {
        private const val PRE_KEYS_DIRECTORY = "preKeys"
    }

    private val filePath: FilePath = FilePath(homeDirectory, PRE_KEYS_DIRECTORY)

    override fun loadPreKey(preKeyId: Int): PreKeyRecord {
        storageHandler.readFile(filePath, preKeyId.toString())?.let { json ->
            val encryptionDto = gson.fromJson(json, EncryptionDto::class.java)
            return PreKeyRecord(encryptionDto.bytes)
        }
        throw InvalidKeyIdException("PreKey not found.")
    }

    override fun storePreKey(preKeyId: Int, record: PreKeyRecord?) {
        record?.let {
            storageHandler.transformToJsonAndSaveToFile(
                filePath,
                preKeyId.toString(),
                EncryptionDto.createPreKeyDto(it.serialize())
            )
        }
    }

    override fun containsPreKey(preKeyId: Int): Boolean {
        return storageHandler.hasFile(filePath, preKeyId.toString())
    }

    override fun removePreKey(preKeyId: Int) {
        storageHandler.deleteFile(filePath, preKeyId.toString())
    }
}
