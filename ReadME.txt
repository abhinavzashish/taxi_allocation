Tools and softwares required for project:-

1. Java 8 Oracle JDK
2. maven
3. postgresQL database
4. IDE like eclipse or Intellij-IDEA




Process:-
1. Create database "taxitesting" with usename as "abhinav" and password as "capital". (these can be configured in application.yml
2. Create file /opt/apps/taxitesting/logs/taxi.log with access as ${user} and not as root.
2. Build (mvn clean install) and start application (java -jar taxi.jar)
3. Sequences and tables should get created on startup of application
4. Use APIs:-
    (look at API definition for requests or samples below)
    1. http://localhost:8081/taxi/insert/rider to insert rider details
    2. http://localhost:8081/taxi/insert/driver to insert driver details
    3. http://localhost:8081/taxi/populate/location to insert location of driver
    4. http://localhost:8081/taxi/search/trip to search for a driver for a given rider

5. Logic for driver search:-
    -Looks for all the drivers who are not engaged and whose pings are collected "x" minutes ago.
    -Then filters out drivers based on distance "d" and get the driver(if any) whose location is latest.





Sample Api inputs:-

1. curl -X POST \
     http://localhost:8081/taxi/search/trip \
     -H 'Accept: application/json' \
     -H 'Content-Type: application/json' \
     -H 'Postman-Token: f98700db-887d-411d-a635-b31ad8d1e08e' \
     -H 'cache-control: no-cache' \
     -d '{
   	"sourceLatitude":"12.9777664",
   	"sourceLongitude":"77.705216",
   	"riderId":"6515ed65-edf2-4e2e-a3f9-9d51759c81c9"
   }'

2. curl -X POST \
     http://localhost:8081/taxi/insert/rider \
     -H 'Accept: application/json' \
     -H 'Content-Type: application/json' \
     -H 'Postman-Token: 2a36d239-4c69-4663-aa4d-6373283be0ec' \
     -H 'cache-control: no-cache' \
     -d '{
   	"name": "R K Suraj",
   	"address": "somewhere"
   }'

3. curl -X POST \
     http://localhost:8081/taxi/insert/driver \
     -H 'Accept: application/json' \
     -H 'Content-Type: application/json' \
     -H 'Postman-Token: c354f20e-22a3-4823-873d-7aa735f009bf' \
     -H 'cache-control: no-cache' \
     -d '{
   	"name":"R K AutoWala",
   	"address": "somewhere dead",
   	"vehicleNumber":"KA11223399",
   	"vehicleType": "CAR"
   }'

4. curl -X POST \
     http://localhost:8081/taxi/populate/location \
     -H 'Accept: application/json' \
     -H 'Content-Type: application/json' \
     -H 'Postman-Token: 35c8be6c-3f51-43d2-9412-ce0bc46b19a4' \
     -H 'cache-control: no-cache' \
     -d '{
   	"latitude": 12.944506,
   	"longitude": 77.702066,
   	"accuracy": 1.0,
   	"driverUniqueId": "4d65038f-3735-4466-b8ac-34bdcfd9deb0"
   }'






Rider and driver locations for sample testing:--

Rider  lat
       12.9777664

       long
       77.705216

distance =  10 km (configurable in application.yml)

Driver
1. lat 12.944506 long 77.702066

2. lat 12.947120, long 77.699298

3. lat 12.943482, long 77.695993

4. lat 12.942186, long 77.695308

5. lat 12.918430, long 77.649857

Result:- all drivers fall in the desired distance. Api returns the driver whose location is latest.






Missing sections :
Unit test cases
