#! /bin/bash
curl -X POST -H "Content-Type: application/json" -d '{ "customerId": 2, "consultantId": 1, "appDate": "2020-05-20", "appStart": "11:15:00", "appEnd": "12:15:00", "complaint": "I said wobbly tooth!!" }' 'http://localhost:8080/diary/appointment'
