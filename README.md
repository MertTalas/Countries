
# Countries

A Countries app using the GeoDB Cities API based on MVVM + Clean architecture.

## App Features (v1)

* 100% Kotlin,
* MVVM with Clean Architecture,
* SOLID Principles,
* Android Architecture Components,
* Single activity pattern,
* Kotlin Coroutines,
* Jetpack Navigation,
* Dependency Injection with Hilt,
* SharedPreferences

## v2: upcoming⚠️
* Kotlin Flow
* Room Database instead of SharedPreferences
* Unit test
* UI test
* JavaDoc
  
## Tech stack & Open-source libraries

- 100%  [Kotlin](https://kotlinlang.org/)  based +  [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- Lifecycle - Dispose observing data when lifecycle state changes
- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI related data in a lifecycle conscious way  
  and act as a channel between use cases and ui
- [View Binding](https://developer.android.com/topic/libraries/view-binding) - Support library that generates a _binding class_ for each XML layout file present in that module. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout. In most cases, view binding replaces  `findViewById`.
- [Navigation Component](https://developer.android.com/guide/navigation) Navigation component is the API and the design tool in Android Studio that makes it much easier to create and edit navigation flows throughout your application.

- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection library for Android
- [Retrofit](https://square.github.io/retrofit/) - Type safe http client and supports coroutines out of the box.
- [GSON](https://github.com/square/moshi) - JSON Parser
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Concurrency design pattern for asynchronous programming.

- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines
- [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences) - Save data

- [Timber](https://github.com/JakeWharton/timber) - Logger with a small, extensible API which provides utility on top of Android's normal  `Log` class

  
## API Usage ("https://rapidapi.com/wirefreethought/api/geodb-cities/")
BaseUrl: "https://wft-geo-db.p.rapidapi.com/v1/geo/"

#### getCountries()

```https
  GET /countries
```

| parameter | type     | description                |
| :-------- | :------- | :------------------------- |
| `@Query("limit")` | `String` | **Optional**. limit number |

#### getCountryDetailById(countryId: String)

```https
  GET /countries/{countryId}
```

| Parametre | Tip     | Açıklama                       |
| :-------- | :------- | :-------------------------------- |
| `@Path("countryId")` | `String` | **Required**. id of the requested country |
  
## Screenshots

![Uygulama Ekran Görüntüsü](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

  
