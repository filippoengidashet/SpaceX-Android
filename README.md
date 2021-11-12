# SpaceX-Android

#### Features

* Show Space X company Information in the header
* Show list of all rocket launches
* Filter launches based on year, launch success & ordered by ASC or DESC
* Show launch detail in either Article, Wikipedia or Video 

#### Added 3rd party SDK

* Hilt (Dependency Injection)
* Navigation Component (Navigation system)
* Room(Database)
* Coroutines (light weight thread computation)
* Retrofit (Rest Api Integration)
* Android Arch (View Model, Livedata, etc.)
* Glide (Image loading and caching)
* Mockito, Junit, Espresso (Testing)
* Code linting with Ktlint & Detekt

#### Architecture - MVVM

![Architecture](https://github.com/filippoengidashet/SpaceX-Android/blob/main/files/arch.png)

* The App is architected using Google’s recommended pattern
* It survives continuous operation on the model side even on screen rotation, then once the view is ready it’ll be automatically reflected via the ViewModel
* Is not gonna cause any memory leak as it’s tied to the component lifecycle (Fragment)

#### Future Improvements

* Code is tested, but it's possible to extend coverage with more time
* Design can be improved with great looks, UI/UX, colours and typography


License
-------

    ![Licence](https://github.com/filippoengidashet/SpaceX-Android/blob/main/LICENSE)
