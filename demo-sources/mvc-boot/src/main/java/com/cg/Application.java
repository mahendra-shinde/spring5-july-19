package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
