## Calendar
* Allows you to manage a dentist's appointment booking calendar.


## Implementation notes
* Customer and consultant data is minimal as this is only intended to show the concepts required
 to maintain an appointment diary.
* Other services such as insurance and billing could be added by extending this application.
* My groovy code looks like Java (as can be seen in the spock unit tests).
* Some calls to backend services have been simulated if another service carrying out an identical
 process for different data already exists for the purpose of this exercise. See `ScheduleService.loadSchedule`
  and `ScheduleService.loadSlotSizes`.

## Design assumptions
### User
* Front end assumes 'Dr. John' is logged in. This could fairly easily be changed by adding
 credential based log in which would present the user's own diary.
 * Furthermore, diary's could be shared by adding role based access; eg. view diary, update diary
 , delete entry. 
### Dentist schedule
* The dentist's schedule is currently held in the database.
* There are two daily periods: morning and afternoon, both provide slots of 15 minutes.
* The data is retrieved by a simple service, however this service could be changed to access
 another microservice accessing a more complex scheduling system for example which could be used
 by the dentist to specify their own schedule in that case only the ScheduleClient service and
  the SlotGenerator would have to be updated along with possibly some json annotations. No
   business logic or workflow would be affected, purely the interface to the backend system.
 
 
## Testing
#### Running the application
* Clone this repository
* Run `mvn install`. This will pull down all dependencies, run tests and produce the test report.
* Run `mvnw spring-boot:run` to start up the backend service.

#### Browsing the database
* Once the application is running open your browser and navigate to:
http://localhost:8080/h2-console/
* The login details are:

|field|value|
|-------|-------|
|JDBC URL|jdbc:h2:mem:diary|
|User Name|sa|
|Password|password|

#### Testing the backend
* Retrieve data for this week
http://localhost:8080/diary/slots
* Retrieve data for a consultant for a date range
http://localhost:8080/diary/slots?consultantId=1&rangeStart=2020-05-20&rangeEnd=2020-05-21
* Or the default which is a full working week
http://localhost:8080/diary/slots?consultantId=1
* Check an individual slot
http://localhost:8080/diary/slot?date=2020-05-20&time=13:45