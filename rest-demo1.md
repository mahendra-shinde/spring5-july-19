## Rest service demo

REpresentational State Transfer

1.  Create new Maven project

    ```yaml
    Projectname:    restdemo1
    GroupId:        com.cg
    Packaging:      jar
    ```

2.  Modify `pom.xml` add parent depenendency and spring boot web starter dependency:

    ```xml
    <properties>
		<maven.compiler.target>1.8</maven.compiler.target>
		<maven.compiler.source>1.8</maven.compiler.source>
	</properties>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-parent</artifactId>
		<version>2.1.4.RELEASE</version>

	</parent>
	<dependencies>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>
    ```

3.  Create `com.cg.Application.java` as startup class

    ```java
    @SpringBootApplication
    public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }

    }
    ```

4.  Create `application.properties` inside `src/main/resources` directory.

    ```ini
    server.port=5000
    ```

5.  Create new Controller class `com.cg.controllers.HomeController`

    ```java
    @RestController
    @RequestMapping("/test")
    public class HomeController {

        /**
        * Process GET REQUEST on localhost:5000/test/	
        * @return
        */
        @GetMapping("/")
        public String testGet() {
            return "This is GET request";
        }

        /**
        * Process POST REQUEST on localhost:5000/test/	
        * @return
        */
        
        @PostMapping("/")
        public String testPost() {
            return "This is POST request";
        }
        
        @PutMapping("/")
        public String testPut() {
            return "This is PUT request";
        }
        
        @DeleteMapping("/")
        public String testDelete() {
            return "This is DELETE request";
        }   
        
    }
    ```

6.  Run application as **Java Application**

7.  Launch _Postman_ to test the application.

    Method type: GET
    
    URL:         http://localhost:5000/test/
    
    Click SEND button

    Repeate the above process for all other method types: PUT, POST, DELETE

