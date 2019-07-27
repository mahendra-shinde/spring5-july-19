## Exception handling in REST Services

1.  Copy the project `rest-demo2` with new name `rest-demo4`

2.  Open `pom.xml` file and rename the artifact id and name to `rest-demo4`

    ```xml
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.cg</groupId>
    <artifactId>rest-demo4</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>rest-demo4</name>
    ```

3.  Create a new User defined exception `com.cg.exceptions.ApplicationException`

    ```java
    public class ApplicationException extends RuntimeException {
	
        public ApplicationException(String message) {
            super(message);
        }
    }
    ```

4.  Modify `com.cg.services.CountryService` class, make it throw `ApplicationException` whenever DAO method fails.

    ```java
    @Service
    public class CountryService {

        @Autowired private CountryDAO dao;
        
        public List<Country> byContinent(String continent){
            List<Country> countries = dao.findByContinent(continent);
            if(countries == null || countries.isEmpty()) {
                throw new ApplicationException("No countries found in continent "+continent);
            }
            return countries; 
        }
        
        public Country byCode(String code) {
            Country country = dao.findByCode(code);
            if(country == null) {
                throw new ApplicationException("Country with code "+code+" did not exists!");
            }
            return country;
        }
        
        public void create(Country country) {
            Country temp = dao.findByCode(country.getCode());
            if(temp==null)
            {
                dao.save(country);
            }else
                throw new ApplicationException("Country "+country.getCode()+ " already exists!");
        }
        
        public void update(Country country) {
            Country temp = dao.findByCode(country.getCode());
            if(temp!=null)
            {
                dao.update(country);
            }else
                throw new ApplicationException("Country "+country.getCode()+" didn't exists!");
        }
    }
    ```
5.  Run the Application.java and Test following URLs (Use Web Browser)

    http://localhost:5000/countries/code/UK

    http://localhost:5000/countries/continent-europe

    Expected:   You must be getting Server Error 500

6.  Create a new Global Exception Handler class `com.cg.exceptions.AppExceptionHandler`

    ```java
    //This class is NOT a controller, but an Assistant to ALL controller within Application
    @ControllerAdvice 
    public class AppExceptionHandler {

        /** 
        * Capture all Exceptions of type ApplicationException,
        * and return a new HttpResponse: Message from Exception and Http Status CODE 404
        * @param exception
        * @return
        */
        @ExceptionHandler({ApplicationException.class})
        public ResponseEntity<String> handleError(ApplicationException exception){
            System.out.println("Exception handled");
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    ```

7.   Run the Application.java and Test following URLs (Use Web Browser)

    http://localhost:5000/countries/code/UK

    http://localhost:5000/countries/continent-europe

    Expected:   You must be getting Http status 404 with Exception message.