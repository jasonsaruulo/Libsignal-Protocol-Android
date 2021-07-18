package com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption

import org.whispersystems.libsignal.IdentityKey
import org.whispersystems.libsignal.IdentityKeyPair
import org.whispersystems.libsignal.state.SignedPreKeyRecord

/**
 * To avoid direct nested array when up-/downloading.
 * Gson can also not create JSON received from backend for Signal Protocol.
 */
class EncryptionDto private constructor(val bytes: ByteArray) {

    companion object {
        private const val IDENTITY_KEY_PAIR = "identityKeyPair"
        private const val IDENTITY_KEY = "identityKey"
        private const val PUBLIC_IDENTITY_KEY = "publicIdentityKey"
        private const val PRE_KEY = "preKey"
        private const val SESSION = "session"
        private const val SIGNED_PRE_KEY = "signedPreKey"

        fun createIdentityKeyPairDto(identityKeyPair: IdentityKeyPair): EncryptionDto {
            return EncryptionDto(identityKeyPair.serialize())
        }

        fun createPreKeyDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createPublicIdentityKeyDto(identityKey: IdentityKey): EncryptionDto {
            return EncryptionDto(identityKey.serialize())
        }

        fun createIdentityKeyDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createSessionDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createSignedPreKeyDto(signedPreKeyRecord: SignedPreKeyRecord): EncryptionDto {
            return EncryptionDto(signedPreKeyRecord.serialize())
        }
    }
}
