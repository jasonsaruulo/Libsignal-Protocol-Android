package com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption

import com.shafiq.saruul.libsignalprotocolandroid.handlers.HandlerModule.Companion.HOME_DIRECTORY
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.FilePath
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import javax.inject.Inject
import javax.inject.Named

class ExampleEncryptionHandler @Inject constructor(
    @Named(HOME_DIRECTORY) private val homeDirectory: String,
    private val storageHandler: StorageHandler
) :
    EncryptionHandler {

    companion object {
        private const val ENCRYPTION_DIRECTORY = "encryption"

        private const val ENCRYPTION_DATA_UPLOADED = "encryptionDataUploaded"
    }

    private val filePath: FilePath = FilePath(homeDirectory, ENCRYPTION_DIRECTORY)

    override fun uploadEncryptionData() {
        if (readEncryptionDataUploaded()) {
            // TODO: Check whether pre keys need update
        } else {
            // TODO: Create identity key pair, registration id, signed pre key, serialised pre key and store on server
            saveEncryptionDataUploaded()
        }
    }

    private fun saveEncryptionDataUploaded() {
        storageHandler.saveToFile(filePath, ENCRYPTION_DATA_UPLOADED, true.toString())
    }

    private fun readEncryptionDataUploaded(): Boolean {
        storageHandler.readFile(filePath, ENCRYPTION_DATA_UPLOADED)?.let { data ->
            return data.toBoolean()
        }
        return false
    }
}
