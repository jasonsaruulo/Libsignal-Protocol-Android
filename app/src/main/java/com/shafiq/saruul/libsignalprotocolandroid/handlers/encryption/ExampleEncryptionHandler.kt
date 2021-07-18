package com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption

import com.shafiq.saruul.libsignalprotocolandroid.handlers.HandlerModule.Companion.HOME_DIRECTORY
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.FilePath
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import org.whispersystems.libsignal.state.SignedPreKeyStore
import org.whispersystems.libsignal.util.KeyHelper
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlin.collections.ArrayList

class ExampleEncryptionHandler @Inject constructor(
    @Named(HOME_DIRECTORY) private val homeDirectory: String,
    private val preKeyStore: PreKeyStore,
    private val signedPreKeyStore: SignedPreKeyStore,
    private val storageHandler: StorageHandler
) :
    EncryptionHandler {

    companion object {
        private const val ENCRYPTION_DIRECTORY = "encryption"

        private const val UUID_FILE = "uuid"
        private const val ENCRYPTION_DATA_UPLOADED_FILE = "encryptionDataUploaded"
        private const val LOCAL_IDENTITY_KEY_PAIR_FILE = "identifyKeyPair"
        private const val LOCAL_REGISTRATION_KEY_FILE = "localRegistrationKey"

        private const val START_PRE_KEY_ID = 0
        private const val NUMBER_OF_PRE_KEY_IDS = 100
        private const val SIGNED_PRE_KEY_ID = 17
    }

    private val filePath: FilePath = FilePath(homeDirectory, ENCRYPTION_DIRECTORY)

    override fun uploadEncryptionData() {
        if (readEncryptionDataUploaded()) {
            // TODO: Check whether pre keys need update
        } else {
            val uuid = getOrGenerateUuid()
            val identityKeyPair = KeyHelper.generateIdentityKeyPair()
            val registrationId = KeyHelper.generateRegistrationId(false /* extendedRange */)
            val signedPreKey = KeyHelper.generateSignedPreKey(identityKeyPair, SIGNED_PRE_KEY_ID)

            storageHandler.transformToJsonAndSaveToFile(
                filePath,
                LOCAL_IDENTITY_KEY_PAIR_FILE,
                EncryptionDto.createIdentityKeyPairDto(identityKeyPair)
            )
            storageHandler.saveToFile(
                filePath,
                LOCAL_REGISTRATION_KEY_FILE,
                registrationId.toString()
            )
            signedPreKeyStore.storeSignedPreKey(signedPreKey.id, signedPreKey)

            val serializedPreKeys = createSerializedPreKeys()

            val publicIdentityKeyDto =
                EncryptionDto.createPublicIdentityKeyDto(identityKeyPair.publicKey)
            val signedPreKeyDto = EncryptionDto.createSignedPreKeyDto(signedPreKey)

            // TODO: Store on server
            saveEncryptionDataUploaded()
        }
    }

    private fun getOrGenerateUuid(): String {
        storageHandler.readFile(filePath, UUID_FILE)?.let {
            return it
        }
        val uuid = UUID.randomUUID()
        storageHandler.saveToFile(filePath, UUID_FILE, uuid.toString())
        return uuid.toString()
    }

    private fun createSerializedPreKeys(): ArrayList<EncryptionDto> {
        val preKeys = KeyHelper.generatePreKeys(START_PRE_KEY_ID, NUMBER_OF_PRE_KEY_IDS)
        preKeys.forEach { preKeyRecord ->
            preKeyStore.storePreKey(preKeyRecord.id, preKeyRecord)
        }
        val serializedPreKeys = ArrayList<EncryptionDto>()
        preKeys.forEach { preKey ->
            serializedPreKeys.add(EncryptionDto.createPreKeyDto(preKey.serialize()))
        }
        return serializedPreKeys
    }

    private fun saveEncryptionDataUploaded() {
        storageHandler.saveToFile(filePath, ENCRYPTION_DATA_UPLOADED_FILE, true.toString())
    }

    private fun readEncryptionDataUploaded(): Boolean {
        storageHandler.readFile(filePath, ENCRYPTION_DATA_UPLOADED_FILE)?.let { data ->
            return data.toBoolean()
        }
        return false
    }
}
