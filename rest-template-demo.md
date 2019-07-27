## RestTemplate

Easy to use component from spring, to call REST services.

- RestTemplate can be used from any type of application.

- RestTemplate do not depend on Spring Context or Spring IOC Container.

- Part of `spring-web` maven dependency.

1. Create new maven project

    ```yaml
    Projectname:    rest-test
    groupId:        com.cg
    packaging:      JAR
    artifactId:     rest-test
    ```
2.  open `pom.xml` and add these two dependencies:

    ```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>5.1.6.RELEASE</version>
        </dependency>

    <!-- For Converting Java Object into JSON -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.9</version>
        </dependency>
    </dependencies>
    ```

3.  Copy all entities `com.cg.entities` from Rest service project `rest-demo3` [preserve the full package name].

4.  Create a main class `com.cg.Main`

    ```java
    public class Main {

	
        private static final String BASE_URL = "http://localhost:5000/countries/";
        public static void main(String[] args) {

            RestTemplate template = new RestTemplate();
            Country country = template.getForObject(BASE_URL+"code/IN", Country.class);
            System.out.println("Found country: "+country.getName());
            
            Country cn = new Country("NN","Narnia","Antarctica","Wakad");
            ResponseEntity<String> response =  template.postForEntity(BASE_URL+"/new", cn, String.class);
            System.out.println("Response "+response.getBody());
        }
    }

    ```
5.  Run `rest-demo3` the service application, and then run current application.