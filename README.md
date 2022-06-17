![platform](https://shields.io/badge/platform-android-green.svg)
![min-API](https://shields.io/badge/min--API-23-brightgreen.svg)
![max-API](https://shields.io/badge/max--API-31-brightgreen.svg)

# Jetpack Compose Playground

This repository contains a set of features that attempts to use the latest libraries and tools. Try to follow Android design and development best practices and is intended to be a useful reference for developers.

Each feature has two modules, one originally made with XML and another with Jetpack Compose, so you can see the difference between them and help you learn and understand more about Compose in Android.

## 🧬 Features

### Transaction

A feature that simulates a send money to a person that lives in another country, making the usage of [open exchange rates](https://openexchangerates.org/) API to get the exchanged value for the given recipient.

<img src="showcase.gif" alt="App running" width="25%" height="25%" />

## 💻 Requirements

It is required the latest [Android Studio Arctic Fox](https://developer.android.com/studio) (or newer) to be able to build the app.

## 🔑 API keys

You need to supply API / client keys for the services the app uses:

- [open exchange rates](https://openexchangerates.org/)

You can find information about how to gain access via the relevant links.

Once you obtain the keys, you can set them in your `local.properties`:

```
# Get these from https://openexchangerates.org/
OPEN_EXCHANGE_APP_ID=<YOUR_APP_ID>
```

## ✅ Code quality

This project uses the following quality tools to maintain consistent codebases:

- [ktlint](https://github.com/pinterest/ktlint), a check style tool to help format our code and make it better for understanding;
- [detekt](https://github.com/detekt/detekt), a static code analysis tool for Kotlin;
- [kover](https://github.com/Kotlin/kotlinx-kover), a code coverage tool for Kotlin;

Always make sure to run `./gradlew detekt ktlintCheck koverVerify` before opening a pull request to see if you have any code style issues and avoid CI server check errors.