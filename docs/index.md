## Summary

Sno is going to be a personal ski app intended for users who enjoy skiing or snowboarding. Users of the app can customize a profile and add personal gear they own to a catalogue, track their distance traveled throughout the season, as well as logging the total days on the skiResort. 
A section in the app will have a myriad of skiResorts across the United States that they can choose from, adding to their favorites for quick access to check the weather, trail maps, and other information regarding the skiResort and its weather.

Intended Functionality:

  * Log personal gear
	
  * Track distanced traveled while riding throughout the season
	
  * Track total days ridden in a season
	
  * Display a variety of skiResorts that contains a link to their respective weather stats and trail maps for what is available.

## Intended users

* People who like to ski or snowboard

    > As someone who goes skiing often, I want to be able to log my gear and keep track of my distance traveled over my days throughtout the season so I can catalogue my ski season to see my results post-season.

* People who are interested in the skiResort stats and weather

    > As someone who can go skiing at multiple skiResorts, I want to be able to easily see each skiResorts weather forecast and status so I can easily decide if I should go skiing that day, go to a different skiResort, or just cancel altogether.

## Functionality

* Users can create a profile that has a list of the users favorite/home skiResorts as well as seeing various stats, such as distance traveled and total days logged. They can also choose to log their gear in the app to catalogue.

* Users can select a skiResort of those listed to see the skiResorts stats such as the weather forecast and trail maps.

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

* Real Time Weather:

    * [OpenWeatherMap API](https://rapidapi.com/community/api/open-weather-map)
	
	* [Weatherbit API](https://rapidapi.com/weatherbit/api/weather)
	
	  * Used to track the weather data pertaining to various skiResorts across the United States. The app can function without this service.
	
* Trail Map APIs:
	
	* [Trail API](https://rapidapi.com/trailapi/api/trailapi)

	* [Ski Trail Map API](https://www.powderproject.com/data)
	
	  * Used to track trails for various skiResorts across the United States. The app is functional without this service.

* [Google Maps](https://cloud.google.com/maps-platform/products)

  * Potentially used to view the area surrounding the skiResorts in relation to the weather. The app is functional without the service.

## Stretch goals/possible enhancements 

* Real time gps tracking that tracks the users vertical feet and can post their stats to a leaderboard. The real time tracking can see what trails the user has taken and compare it to the trail map.

* Have all skiResort stats to every skiResort in the USA, or even globally, and be able to see all necessary stats for that skiResort.

* Have a section for tips and tricks--tutorials for tricks, general riding, fundamentals to improving.

* Save weather data for skiResorts to the server to see latest weather report prior to going offline.

## [Wireframe](sno-wireframe-description.md)

## [Entity-relationship Diagram](sno-erd-description.md)