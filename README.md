# Libsignal Protocol Android
A simple application using [libsignal-protocol](https://github.com/signalapp/libsignal-protocol-java)
for end to end encryption. The intention is to show/describe steps required to make libsignal protocol
work for your Android project.

## Add Libsignal Protocol dependency
Go to your `build.gradle` and add `implementation 'org.whispersystems:signal-protocol-android:2.8.1'`.

Check [libsignal-protocol](https://github.com/signalapp/libsignal-protocol-java) for newer versions if you want/need to.

## (Optional) Dependency injection setup
This project uses [Dagger](https://dagger.dev/) for dependency injection. As this project's main idea is not to show how to use Dagger, there will be no detailed description. Actually, you do not have to use dependency injection at all to make the Libsignal Protocol work for your Android project.

## Add a storage handler
You need to save to and read from the device. This project has a `StorageHandler` interface implemented by `ExampleStorageHandler`. The `ExampleStorageHandler` should work fine but feel free to use the interface and implement the required methods on your own if you have different specifications. You can of course come up with a completely different approach.

If you want to use the provided example implementation, you need to add [Gson](https://github.com/google/gson) and [AndroidX Security Crypto](https://developer.android.com/jetpack/androidx/releases/security) dependencies.
```
implementation 'com.google.code.gson:gson:2.8.6'
implementation 'androidx.security:security-crypto:1.0.0'
```
