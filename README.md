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
* Additional rest services could be added to provide available services based on user / role post
 logon with a rest call providing end points for each service, eg. view diary URI, make appointments
  URI, etc. 

## Design assumptions
### User
* Front examples assume 'Dr. John' is logged in. This could fairly easily be changed by adding
 credential based log in which would present the user's own diary.
 * Furthermore, diary's could be shared by adding role based access; eg. view diary, update diary
 , delete entry.
### Dentist schedule
* The dentist's schedule is currently held in the database.
* There are two daily periods: morning and afternoon, both provide slots of 15 minutes.
* The data is retrieved by a simple service, however this service could be changed to access
 another microservice, which could in turn access a more complex scheduling system for example. That
 could be used by the dentist to specify their own schedule in that case only the ScheduleClient
  service and the SlotGenerator would have to be updated along with possibly some json annotations. No
   business logic or workflow would be affected, purely the interface to the backend system.
 
 
## Testing

### Unit test coverage
* Some items which are simply wiring managed by spring have no unit tests as Pivotal test spring
, for example: repositories (which are just interfaces) and service methods which are just a
 proxy for those repositories: `AppointmentService.loadAppointments`.
* Java beans which are nothing but getters and setters have no coverage.

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

#### Testing scripts
Included in src/test/scripts
You will have to update the dates in the script as they will go out of range dependant on when you
 run them
* Retrieve data for this week
getWeeklySlots.sh
* Retrieve data for a consultant for a date range
getDateRangeSlots.sh
* Check an individual slot
checkSingleSlot.sh
* Create a new appointment
makeAppointment.sh
* Delete an appointment
deleteAppointment.sh

#### Testing the backend
* Retrieve data for this week (using GET)
http://localhost:8080/diary/slots/1

* Retrieve data for a consultant for a date range (using GET)
http://localhost:8080/diary/slots/1?rangeStart=2020-05-20&rangeEnd=2020-05-21

* Check an individual slot (using GET)
http://localhost:8080/diary/slot/1?date=2020-05-21&appStart=14:30&appEnd=14:45

* Create a new appointment (using POST)
http://localhost:8080/diary/appointment
with payload
```
{
  "customerId": 2,
  "consultantId": 1,
  "appDate": "2020-05-20",
  "appStart": "11:15:00",
  "appEnd": "12:15:00",
  "complaint": "I said wobbly tooth!!"
}
```
* Attempt to create a new appointment when one already exists (using POST)
http://localhost:8080/diary/appointment
with payload
```
{
  "customerId": 2,
  "consultantId": 1,
  "appDate": "2020-05-21",
  "appStart": "11:15:00",
  "appEnd": "12:15:00",
  "complaint": "I said wobbly tooth!!"
}
```
* Delete an appointment (using DELETE)
http://localhost:8080/diary/appointment/3