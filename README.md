# Keyword Score Calculator
## About the solution
Based on autocompletion Amazon web service, the entered keyword score will be calculated by KeywordCalculatorService.
The calculation service analyzes all the returned result of Amazon API and searches for an exact match of keyword.
General products usually have all 10 results (such as Apple watch) by adding more details to product the Api results
reduces (such as Apple watch women). The project assumption is more result is related to the frequency of the search and
if the keyword does not appear alone and be a part of another world means there is no product search available for the 
keyword. 

The solution transforms the input keyword to a URL compatible keyword. 
Test code coverage has been checked with Jacoco.


## Libraries & Tools
-	Spring Boot 2.1.6.RELEASE
-	Swagger API
-	Maven


### Main Class :
```
slcs\src\main\java\com\abs\slcs
com.abs.slcs.SlcsApplication.java
```
### Controller:
```
com.abs.slcs.controller.KeywordScoring
test:
slcs\src\test\java\com\abs\slcs\controller
```
### Service: 
```
com.abs.slcs.service.KeywordScoreCalculatorServiceImpl
test:
slcs\src\test\java\com\abs\slcs\service
```

### to run the application:
```
mvn spring-boot:run
```
### to run the test:
```
mvnw test
```
### to check the test code coverage (JACOCO):
```
slcs\target\site\jacoco\index.html 
```
### To See the result:

-	Run the application
-	In browser with swagger:
```
	http://localhost:8080
```


