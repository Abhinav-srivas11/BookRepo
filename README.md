# BookRepo
Description : An application that uses an API to fetch a list of books and allows searching based on ID or fuzzy search on title. 

Technologies used : Java 8, Hibernate, Jackson, Springboot, H2 database

How it works?

A repo of all books exist on public AWS that is returned as an Json array when requested.
BookRepo application loads all the books into H2 database once the Springboot application is started up i.e loading happens when application is ready.
This helps reduce overall startup time of the application ,since books are loaded after app is ready and are accessible as soon as startup is finished.

With all the books loaded into the database
