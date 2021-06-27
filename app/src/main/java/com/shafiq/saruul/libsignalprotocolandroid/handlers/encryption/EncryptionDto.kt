package com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption

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

        fun createIdentityKeyPairDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createPreKeyDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createPublicIdentityKeyDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createIdentityKeyDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createSessionDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }

        fun createSignedPreKeyDto(bytes: ByteArray): EncryptionDto {
            return EncryptionDto(bytes)
        }
    }
}
