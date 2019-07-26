## Rest service demo

REpresentational State Transfer

REST Service can produce or consume data in multiple formats. The most common formats are:
    1. JSON (Default for RESTful Services)  MIME: application/json
    2. XML (Needs @XmlRootElement annotation) MIME: application/xml
    3. HTML MIME: text/html
    4. Plain Text MIME: text/plain

    NOTE: text/html and text/plain are for browser based applications. most other client applications prefer either JSON or XML only.


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

8.  Create new Entity class `com.cg.entities.Message`

    ```java
    @XmlRootElement
    public class Message {

        private String text;
        private Date date;
        
        public Message(String text) {
            super();
            this.text = text;
            this.date = new Date();
        }
        public Message() { }
        /// Getters and Setters
    }
    ```
    > Set JRE for Current project to either `Workspace Default` or `Java SE-1.8` mode
      Or else, you cannot import @XmlRootElement annotation.
      

9.  Modify `com.cg.controllers.HomeController` to include Produces Type

    ```java
    @RestController
    @RequestMapping("/test")
    public class HomeController {

        /**
        * Process GET REQUEST on localhost:5000/test/	
        * @return JSON or XML object of type MESSAGE
        */
        @GetMapping(value="/",produces= {"application/json","application/xml"})
        public Message testGet() {
            return new Message("This is GET request");
        }

        /**
        * Process POST REQUEST on localhost:5000/test/	
        * @return JSON or XML object of type MESSAGE
        */
        @PostMapping(value="/",produces= {"application/json","application/xml"})
        public Message testPost() {
            return new Message("This is POST request");
        }
        
        /**
        * Process PUT REQUEST on localhost:5000/test/	
        * @return XML object of type MESSAGE
        */
        @PutMapping(value="/",produces= {"application/xml"})
        public Message testPut() {
            return new Message("This is PUT request");
        }
        

        /**
        * Process DELETE REQUEST on localhost:5000/test/	
        * @return JSON or XML object of type MESSAGE
        */
        @DeleteMapping(value="/",produces= {"application/json","application/xml"})
        public Message testDelete() {
            return new Message("This is DELETE request");
        }           
    }
    ```
10. Run application and test using Postman. In postman user "Request Header" **Accept** with value _application/xml_.

    Later try changing ACCEPT type to _application/json_