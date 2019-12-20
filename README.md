# doctor
In this branch you'll find:

Introduction
------------

The application uses Clean Architecture based on MVVM and Repository patterns. Implemented
Architecture principles follow Google recommended [Guide to app architecture](https://developer.android.com/jetpack/docs/guide).

![Guide to app architecture](screenshots/guide-to-app-architecture.png "Guide to app architecture")

The application is written entirely in Kotlin.

Android Jetpack is used as an Architecture glue including but not limited to ViewModel, LiveData,
Lifecycles, Navigation and Data Binding. See a complete list in "Libraries used" section.

The application does network HTTP requests via Retrofit, OkHttp and GSON.

Kotlin Coroutines manage background threads with simplified code and reducing needs for callbacks.
Combination of Coroutines and Kotlin build in functions (transformation, collections) are preferred

Navigation component manages in-app navigation.

Koin is used for dependency injection.

picasso is used for image loading.

A sample app consist of 3 screens: Splash screen ,list of doctor and doctor details.

Screenshots
-----------



Libraries Used
--------------

The application goal is to show case current Android Architecture state using out of box
Android tools made by Google (Android Jetpack) and 3rd party community driven libraries.

Android Jetpack is a set of components, tools and guidance to make great Android apps. They bring
together the existing Support Library and Architecture Components and arranges them into four
categories:
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate.
  * [Fragment][34] - A basic unit of composable UI.
* Third party
  * [Kotlin Coroutines][91] for managing background threads with simplified code
     and reducing needs for callbacks.
  * [Koin][92] A fast dependency injector.
  * [Retrofit 2][93] A configurable REST client.
  * [OkHttp 3][94] A type-safe HTTP client.
  * [GSON][95] A Json - Object converter using reflection.
  * [picasso][90] Image loading.



Android Studio IDE setup
------------------------
For development, the latest version of Android Studio is required.
The latest version Android Studio I used is  "3.5.3"
