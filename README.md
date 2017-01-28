# Assignment: Week 1 - *Flixter*

**Flixter** is a read-only movie listing app using the Movie Database API..

Submitted by: **Praveen Shangunathan**

Time spent: **20** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
* [X] Displays a nice default placeholder graphic for each image during loading.
* [X] User can pull-to-refresh the popular stream with [SwipeRefreshLayout](http://guides.codepath.com/android/Implementing-Pull-to-Refresh-Guide).
* [X] Lists should be fully optimized for performance with the [ViewHolderPattern](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern).
* [X] Views should be responsive for both [landscape/portrait mode](http://guides.codepath.com/android/Understanding-App-Resources#creating-alternate-resources).
    * In portrait mode, the poster image, title, and movie overview is shown.
    * In landscape mode, the rotated layout should use the backdrop image instead and show the title and movie overview to the right of it.

The following **optional** advanced user stories are implemented:

* [X] State is maintained on scrolling and shifting between orientations.
* [X] Used [Styles and Themes](http://guides.codepath.com/android/Styles-and-Themes).
* [X] For popular movies (i.e. a movie with vote_average greater than 7), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) used.
* [X] Movie details (ratings using RatingBar, votes, and overview) are exposed in a separate activity.
* [X] **Stretch**: Used ButterKnife annotation library to reduce view boilerplate.
* [X] **Stretch**: Added a play icon overlay to popular movies to indicate that the movie can be played.
* [X] **Stretch**: Video posts now play in full-screen using the [YouTubePlayerView](http://guides.codepath.com/android/Streaming-Youtube-Videos-with-YouTubePlayerView).
    * When clicking on a popular movie (i.e. a movie with average greater than 7 of more than 7) the video should be played immediately.

The following **additional** features are implemented:

* [X] Used RecyclerView which is a more advanced and more flexible version of the ListView.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/spraveen316/Flixter/blob/master/Flixter.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Starting the emulator successfully all the time was an issue. Once I had to reboot my machine to get this started.

## License

    Copyright [2017] [Praveen Shangunathan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
