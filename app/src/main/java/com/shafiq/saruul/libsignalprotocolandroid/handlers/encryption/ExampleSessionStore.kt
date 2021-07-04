package com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption

import com.google.gson.Gson
import com.shafiq.saruul.libsignalprotocolandroid.handlers.HandlerModule.Companion.HOME_DIRECTORY
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.FilePath
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import org.whispersystems.libsignal.SignalProtocolAddress
import org.whispersystems.libsignal.state.SessionRecord
import org.whispersystems.libsignal.state.SessionStore
import javax.inject.Inject
import javax.inject.Named

class ExampleSessionStore @Inject constructor(
    private val gson: Gson,
    @Named(HOME_DIRECTORY) private val homeDirectory: String,
    private val storageHandler: StorageHandler
) : SessionStore {

    companion object {
        private const val SESSIONS_DIRECTORY = "sessions"
    }

    private val filePath: FilePath = FilePath(homeDirectory, SESSIONS_DIRECTORY)

    override fun containsSession(address: SignalProtocolAddress?): Boolean {
        address?.let {
            return storageHandler.hasFile(
                filePath.suffix(address.name),
                address.deviceId.toString()
            )
        }
        return false
    }

    override fun getSubDeviceSessions(name: String?): MutableList<Int> {
        val subDeviceSessions = ArrayList<Int>()
        name?.let {
            val data = storageHandler.listFileNames(filePath.suffix(name))
            data.forEach { fileName ->
                subDeviceSessions.add(fileName.toInt())
            }
        }
        return subDeviceSessions
    }

    override fun loadSession(address: SignalProtocolAddress?): SessionRecord {
        address?.let {
            storageHandler.readFile(
                filePath.suffix(address.name), address.deviceId.toString()
            )?.let { json ->
                val encryptionDto = gson.fromJson(json, EncryptionDto::class.java)
                return SessionRecord(encryptionDto.bytes)
            }
        }
        // Start new session
        return SessionRecord()
    }

    override fun deleteSession(address: SignalProtocolAddress?) {
        address?.let {
            storageHandler.deleteFile(filePath.suffix(address.name), address.deviceId.toString())
        }
    }

    override fun deleteAllSessions(name: String?) {
        name?.let {
            storageHandler.deleteDirectory(filePath.suffix(name))
        }
    }

    override fun storeSession(address: SignalProtocolAddress?, record: SessionRecord?) {
        address?.let {
            record?.let {
                storageHandler.transformToJsonAndSaveToFile(
                    filePath.suffix(address.name),
                    address.deviceId.toString(),
                    EncryptionDto.createSessionDto(record.serialize())
                )
            }
        }
    }
}
