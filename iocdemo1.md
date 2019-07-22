## IOC Demo 1 with Spring (XML Configuration)

1.  Create new maven project **iocdemo1**

2.  Add dependency in **pom.xml**:  
    
	Right-click on "pom.xml" and choose _maven_ > _Add Dependency_

    `spring-context` version `5.1.6`

	The `pom.xml` should now look like this:

	```xml
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.cg</groupId>
	<artifactId>iocdemo1</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>iocdemo1</name>
	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.1.6.RELEASE</version>
		</dependency>
	</dependencies>
	</project>
	```


3.  create new java class `com.cg.iocdemo.Address`
	
    ```java
    private String line1, line2, city;
	
	//Create Getters/Setters
	//Create TWO Constructors
	//  1. Empty Constructor (With No Arguments)
	//  2. Parameterized Constructor
    ```

4.  Create new java class `com.cg.iocdemo.Employee`
	
    ```java
    public class Employee {
	private String firstName, lastName;
	private double salary;
	private String designation;
	private Address address;
	
	private List<String> contacts;
	//Getters, Setters & Constructors
    ```

5.  create an XML **spring.xml**

    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
	
	<bean id="emp" class="com.cg.iocdemo1.Employee">
		<property name="firstName" value="Donald" />
		<property name="lastName" value="Trumph" />
		<property name="designation" value="POTUS" />
		<property name="salary" value="1239990" />
		<property name="contacts">
			<list>
				<value>2369869879</value>
				<value>5765765677</value>
			</list>
		</property>

		<property name="address">
			<bean class="com.cg.iocdemo1.Address">
			<constructor-arg value="White house" />
			<constructor-arg value="Washignton DC" />
			<constructor-arg value="Columbia" />
			</bean>
		</property>
	</bean>
    ```
6.  Create new Main class `com.cg.iocdemo.Main`

    ```java
    public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		// Get Bean of Type Employee AND ID emp
		Employee emp = context.getBean("emp", Employee.class);

		// Get Bean with ID emp, but DON'T assign Type
		Employee emp2 = (Employee) context.getBean("emp");

		System.out.println("Emp :" + emp.hashCode());
		System.out.println("Emp2 :" + emp2.hashCode());
		System.out.println("First Phone: "+emp.getContacts().get(0));
	}
    ```

> The complete code could be found [here](./demo-sources/iocdemo1) 