## Entity-relationship diagram

[![ERD](img/sno-erd.png)](pdf/sno-erd.pdf)

The entity-relationship diagram incorporates the following entities:

  * Trip
  
    * Shares a many optional to one mandatory relationship with User
  
  * User
    
    * Shares multiple many optional relationships with Trip, FavoriteMountain, and Gear
  
  * Gear
  
    * Shares a many optional to one mandatory relationship with User
  
  * Mountain
  
    * Shares a many optional to one mandatory relationship with FavoriteMountain
  
  * FavoriteMountain 
  
    * Joint entity for a many to many relationship between User & Mountain)