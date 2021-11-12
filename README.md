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


MIT License
-------

Copyright (c) 2021 Filippo Engidashet

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
