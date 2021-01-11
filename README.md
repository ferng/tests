
# Loan Approval Decision Maker

## Requirements:
Backend implementation only (as confirmed via email).
Java.
SpringBoot.
Mocked decision engine.


## Assumptions:

SSN is assumed to follow the US SSN format (the UK uses NI number).


## Design decisions:
* Minor decisions deviating from what would be a real implementation are all documented as comments
 in the code, for instance magic numbers which would be obtained via some other mechanism in a
 real world implementation, for instance a database or an additional third party rest endpoint.
 
* The requirements state that "b. If service returns score greater than 700 then: i. Calculate
 the Sanctioned loan amount as Annual income/2 as the sanctioned amount". As no other conditions
 are given I have assumed that if the score is less than or equal to 700 the application will be
 rejected, however this behaviour can be changed by updating CLASS.METHOD-------

* Without a backing database we cannot determine whether the same applicant has applied in the last
 30 days. In order to facilitate testing via the exposed rest-endpoint the number of days
 (since the last application) is sent as first two digits in the SSN. The application
 logic is still in place and only ```Applicant.isRecentApplicant``` would have to be changed to
 introduce checks against a database.
 
 * The decision-making engine is also mocked using a similar mechanism. It is accessed via
  validation.ApprovalEngine. In this case different behaviour can be triggered by changing the last digit of the SSN:

| last digit in SSN | returned value |
| ----------- | ----------- |
| 0 | 0 |
| 1 | 699 |
| 2 | returns 700 |
| 3 | returns 701 |

## Usage
* startup the service:
```./mvnw spring-boot:run```

* sample request:
```curl -H "Content-Type: application/json" --request GET --data '{"ssn":"000-00-0000", "loan":60000,"income":32500}' http://localhost:8080/loan/decision```

* no applications in the last 30 days
```curl -H "Content-Type: application/json" --request GET --data '{"ssn":"400-00-0000", "loan ":60000,"income":32500}' http://localhost:8080/loan/decision```

* existing applications in the last 30 days
```curl -H "Content-Type: application/json" --request GET --data '{"ssn":"100-10-0000", "loan ":60000,"income":32500}' http://localhost:8080/loan/decision```

