package com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption

import android.content.Context
import com.google.gson.Gson
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.FilePath
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import org.whispersystems.libsignal.InvalidKeyIdException
import org.whispersystems.libsignal.state.SignedPreKeyRecord
import org.whispersystems.libsignal.state.SignedPreKeyStore
import javax.inject.Inject

class ExampleSignedPreKeyStore @Inject constructor(
    context: Context,
    private val gson: Gson,
    private val storageHandler: StorageHandler
) : SignedPreKeyStore {

    companion object {
        private const val SIGNED_PRE_KEY_DIRECTORY = "signedPreKeys"
    }

    private val filePath: FilePath = FilePath(context, SIGNED_PRE_KEY_DIRECTORY)

    override fun containsSignedPreKey(signedPreKeyId: Int): Boolean {
        return storageHandler.hasFile(filePath, signedPreKeyId.toString())
    }

    override fun storeSignedPreKey(signedPreKeyId: Int, record: SignedPreKeyRecord?) {
        record?.let {
            storageHandler.transformToJsonAndSaveToFile(
                filePath,
                signedPreKeyId.toString(),
                EncryptionDto.createSignedPreKeyDto(record.serialize())
            )
        }
    }

    override fun removeSignedPreKey(signedPreKeyId: Int) {
        storageHandler.deleteFile(filePath, signedPreKeyId.toString())
    }

    override fun loadSignedPreKey(signedPreKeyId: Int): SignedPreKeyRecord {
        storageHandler.readFile(filePath, signedPreKeyId.toString())?.let { json ->
            val encryptionDto = gson.fromJson(json, EncryptionDto::class.java)
            return SignedPreKeyRecord(encryptionDto.bytes)
        }
        throw InvalidKeyIdException("Signed pre key not found.")
    }

    override fun loadSignedPreKeys(): MutableList<SignedPreKeyRecord> {
        val signedPreKeys = ArrayList<SignedPreKeyRecord>()
        storageHandler.listFileNames(filePath).forEach { fileName ->
            signedPreKeys.add(loadSignedPreKey(fileName.toInt()))
        }
        return signedPreKeys
    }
}