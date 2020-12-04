## Summary

Sno is going to be a personal ski app intended for users who enjoy skiing or snowboarding. Users of the app can customize a profile and add personal gear they own to a catalogue, track their distance traveled throughout the season as well as their speed, and logging the total days ridden for a given season. 
A section in the app will have a myriad of ski resorts across the United States that they can choose from, adding to their favorites for quick access to check the weather, trail maps, and other information regarding the ski resort and its weather.

Intended Functionality:

  * Log personal gear
  
  * Track speed with the accelerometer
	
  * Track distanced traveled while riding throughout the season
	
  * Track total days ridden in a season
	
  * Display a variety of ski resorts that contain a link to their respective weather stats and trail maps.
  
My motivation for this app stems from my passion for snowboarding. Snowboarding has been my favorite get away to escape from the burdens of life and enjoy the tranquil environment of the mountain without having to worry about any responsibilities. This app is great for those who want to check the weather from different ski resorts 
, log their days ridden in a given season, their total distance traveled while riding, and track the speed of the rider in real time. Whenever I check the weather, I have to search for each resort individually, or go to the website of the respective ski resort that I am researching, resulting in the process becoming quite cumbersome. This puts all of that information 
into one convenient location for easy access.


## Intended users

* People who like to ski or snowboard

    > As someone who goes skiing often, I want to be able to log my gear and keep track of my distance traveled over my days throughout the season, so I can catalogue my ski season to see my results post-season.

* People who are interested in the ski resort stats and weather

    > As someone who can go skiing at multiple ski resorts, I want to be able to easily see each ski resorts weather forecast and status, so I can easily decide if I should go skiing that day, go to a different ski resort, or just cancel altogether.

## Functionality

* Users can create a profile that has a list of the users favorite/home ski resorts as well as seeing various stats, such as distance traveled and total days logged. They can also choose to log their gear in the app to catalogue.

* Users can select a ski resort of those listed to see the ski resorts stats such as the weather forecast and trail maps.

## Persistent data

* User profile
	
    * Gear
  
    * Trip data (distance traveled, days logged, etc)

* Mountain trail map if downloaded
    
## Device/external services

* [GPS](https://developer.android.com/training/location)

  * Used by the app to track trip data such as distance traveled and start and end time of skiing. The app can function without this service.

* [Accelerometer](https://developer.android.com/guide/topics/sensors/sensors_overview)

  * Used by the app to track a max speed while riding. The app can function without this service.

* [OpenWeatherMap API](https://rapidapi.com/community/api/open-weather-map)

  * Used by the app to track the weather for ski resorts.
		
## [Summary of Current App State](app-summary.md)

## [Wireframe](descriptions/sno-wireframe-description.md)

## [Entity-relationship Diagram](descriptions/sno-erd-description.md)

## [DDL](ddl.md)

## [Technical Requirements & Dependencies](technical-dependencies.md)

## [Javadocs](api/index.html)

## [Copyrights & Licenses](notice.md)

## [Build Instructions](https://github.com/anayadrian1/sno#build-instructions)

## [Basic User Instructions](https://github.com/anayadrian1/sno#basic-user-instructions)
