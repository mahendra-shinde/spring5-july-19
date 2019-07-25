## Creating new Spring boot application using Maven

1. Create new Maven project

    ```yaml
    Projectname:    mvc-boot
    Packaging:      JAR
    GroupId:        com.cg
    ArtifactId:     mvc-boot
    ```

2.  Open `pom.xml` file and add new parent dependency with spring-starter-web

    ```xml
    <parent>
    	<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-parent</artifactId>
  		<version>2.1.4.RELEASE</version> 		
    </parent>
    
    <dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- DO NOT USE VERSION PROPERTY, 
             VERSION IS DERIVED FROM PARENT DEPENDENCY -->
    </dependency>
    </dependencies>
    ```

3.  Create following packages.

    * com.cg
    * com.cg.controllers
    * com.cg.entities
    * com.cg.services
    * com.cg.daos

    NOTE: You can replace com.cg with whatever name you want.
          Even controllers, entities, services and daos can use different name.
          Only thing mandatory is: they all must be in seperate packages.
          And, MAIN Class (with @SpringBootApplication) must be in FIRST package (parent package).

4.  Create the MAIN class in `com.cg` package.

    ```java
    @SpringBootApplication
    public class Application {
        /** This SINGLE line DOES FOLLOWING:
		 * 	1. Create AnnotationConfigApplicationContext
		 *  2. It Uses @ComponentScan for ALL child packages
		 *  	2.1 It picks up HelloController class
		 *  	2.2 Does REQUEST MAPPING to "/"
		 *  3.	It reads application.properties
		 *  	3.1 Found one property "server.port=3000"
		 *  4. Launches Embedded Tomcat server and deploy HelloController
		 *  5. Wait for you to TERMINATE the process	
		*/
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }
    ```

5.  Create new controller `com.cg.controllers.HelloController`

    ```java
    @RestController
    public class HelloController {

        @GetMapping("/") // Home page
        public String hello() {
            return "Hello World";
        }
    }
    ```

6.  Create new file `application.properties` inside `src/main/resources` directory

    ```ini
    server.port = 3000
    ```

7.  Goto `Application.java` and **Run As Java Application**

8.  Open web browser and visit URL: _http://localhost:3000/_