# CabStoreApp

Aplicación al reto de Cabify
The architecture used is Clean Architecture with MVI.
The language used is Kotlin.
Unit tests were included.

Clean architecture was used because with the separation of the data, domain and presentation layers, it allows to have a more ordered and separated code so that it is easier to read and test it.
In addition MVI although adding several classes, they are all small and allows testing the code in a simpler way.

No data persistence was used, to avoid further complicating the challenge, but the products are stored in a singleton object.

Min SDK 21 y Target SDK 32

# Library Stack
* Hilt: For dependency Injection
* Coroutines + Flow: To manage multithreads
* Retrofit/OkHttp/Gson: For service consumption
* View binding: to link code with view
* Navigation Component: To implement the standard navigation on Android

# Unit Test
junit and mockk.