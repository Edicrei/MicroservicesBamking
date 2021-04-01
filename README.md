
## How to run
### Requirements
* Docker
* Maven 

#### Build
`mvn clean package`
#### Generate Docker image
 `docker build -t bank:local -f Dockerfile.local .`
#### Run
`docker run bank:local -p 9091:9091`

* Check application is running: [http://localhost:8080/status](http://localhost:9091/status)

I've decided to use an H2 database, because it's embedded in the server, lightweight and fast.

## Endpoints

* Status: check the service is ready:
[http://localhost:8080/status](http://localhost:8080/status)

* Activate card
[http://localhost:8080/activate_card?cardNumber=11112222333344445555](http://localhost:8080/activate_card?cardNumber=11112222333344445555)

* Get bank accounts:

   * OK query (200): [http://localhost:8080/accounts?cardNumber=1111222233334444&pin=1234](http://localhost:8080/accounts?cardNumber=1111222233334444&pin=1234)

   * ERROR query (400): [http://localhost:8080/accounts?cardNumber=1111222233334444&pin=12345](http://localhost:8080/accounts?cardNumber=1111222233334444&pin=12345)
   
* Get Activity:
   * OK query (200): [http://localhost:8080/activity?cardNumber=1111222233334444&pin=1234](http://localhost:8080/activity?cardNumber=1111222233334444&pin=1234)
   * ERROR query (400): [http://localhost:8080/activity?cardNumber=1111222233334444&pin=12345](http://localhost:8080/activity?cardNumber=1111222233334444&pin=12345)


* Get cash from ATM
   * OK Query [http://localhost:8080/getCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=1](http://localhost:8080/getCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=1)
   * ERROR query [http://localhost:8080/getCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=2](http://localhost:8080/getCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=2)

* Put cash to ATM
   * OK Query [http://localhost:8080/putCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=1](http://localhost:8080/putCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=1)
   * ERROR query [http://localhost:8080/putCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=2](http://localhost:8080/putCash?cardNumber=1111222233334444&pin=1234&quantity=50&atmId=2)