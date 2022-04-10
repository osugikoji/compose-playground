![platform](https://shields.io/badge/platform-android-green.svg)
![min-API](https://shields.io/badge/min--API-23-brightgreen.svg)
![max-API](https://shields.io/badge/max--API-31-brightgreen.svg)

# Penguin Pay Challenge

A prototype send transaction SDK in binary format. The project was built with the following stacks:

* MVVM
* Clean Architecture
* Coroutine
* Retrofit + OkHttp + Moshi
* Koin

## How to use

To initialize the SDK just call the entrypoint and call the launch method.

```kotlin
val penguinPayLauncher = PenguinPayLauncher.create()
penguinPayLauncher.launchSendTransaction(activity)
```

## Sample

The [project sample]() demonstrates how to use the SDK in a practical way.

## Showcase

<img src="showcase.gif" alt="App running" width="25%" height="25%" />