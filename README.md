# Device_Management

### Project Features

  Backend service to store and process data from IOT devices
(Internet of Things devices e.g. sensors, actuators). 

  Cause consider that the technology should support high volumes of read and write requests with small payloads and
be highly available and performant.

   With data may send to server with time frequently, time series,... data can up to millions over time,... also have no complicated data order is required, paging,... so I decide to choose NoSQL Cassandra.

  Using Spring Boot for project Restfult Api help to reduce the boilerplate code, supports autoconfigure, easy to test,...
  

### Technical
- Java 8
- Spring Boot
- Spring Data Cassandra
- Maven
- Lombok
- NoSql Cassandra
- Docker
- Unit Test: Junit 5

### Development time
- Preparation: 1 hour
- Coding: 7 hours (including Unit Tests,...)
- Documentation: 30 minutes
- Building and testing: 2 hours
- Grand total: 10.5 hours 

### How to run

##### Prerequisite: New OS with Docker – Docker compose is installed
* Source
```
https://github.com/sodohoho/devicemanagement.git
```

* Docker

##### Working directory: ./release (contain docker-compose.yml file)
```
docker-compose up
```


### Api List

* US01- POST :  http://localhost:8080/api/devices

Sample Request
````
   {
    "deviceId": "ef69c5c3-0050-4b9a-8193-bf3e720e1aab",
    "latitude": 4,
    "longitude": 4,
    "data": {
        "humidity": 4,
        "temparature": {
            "unit": "C",
            "value": 4.0
        }
     }
  }
````    
Sample Respone 
````
   {
    "code": "S01",
    "data": {
        "deviceId": "ef69c5c3-0050-4b9a-8193-bf3e720e1aab",
        "latitude": 4.0,
        "longitude": 4.0,
        "data": {
            "humidity": 4,
            "temparature": {
                "unit": "C",
                "value": 4.0
            },
            "timestamp": "2021-11-20T22:05:00.3756213"
        }
    },
    "error": null
}
````
* US02- GET : http://localhost:8080/api/devices/ef69c5c3-0050-4b9a-8193-bf3e720e1aab?startTime=2021-11-10T17:14:39&endTime=2021-11-20T22:14:11

Sample Respone 
````
   {
    "code": "S01",
    "data": {
        "deviceId": "ef69c5c3-0050-4b9a-8193-bf3e720e1aab",
        "latitude": 4.0,
        "longitude": 4.0,
        "data": [
            {
                "humidity": 3,
                "temparature": {
                    "unit": "C",
                    "value": 3.0
                },
                "timestamp": "2021-11-20T22:00:18.698"
            },
            {
                "humidity": 4,
                "temparature": {
                    "unit": "C",
                    "value": 4.0
                },
                "timestamp": "2021-11-20T22:00:32.339"
            }
        ]
    },
    "error": null
}
````

![alt text](https://i.ibb.co/N1dqD7f/1.png)
![alt text](https://i.ibb.co/6PFPg4S/2.png)
![alt text](https://i.ibb.co/ykjgLtq/3.png)
