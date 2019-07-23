## Creating Spring MVC Project (As Maven Project)


1. Create new Maven project 
   
	```yml
	Name: 		webdemo1
	Packaging: 	WAR
	GroupId: 	com.cg
	ArtifactId: 	webdemo1
	```

2. Open POM.XML and ADD new dependencies:

	Switch to "Dependencies" TAB
	Click on "Add" Button
	
	Search for following dependencies
		
		spring-webmvc, version 5.1.6.RELEASE
		
		jstl, version 1.2

3. Right click on Project and choose "Java EE Tools" > Generate Deployment Descriptor Stub ("web.xml" file)

4. Open web.xml file and add following lines:
		(Make sure all these tags written are before `</web-app>` tag

	```xml
  	<servlet>
  	<servlet-name>dispatcher</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
   	<param-name>contextConfigLocation</param-name>
   	<param-value>
         /WEB-INF/spring.xml
   	</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
   	</servlet>
	<servlet-mapping>
  		<servlet-name>dispatcher</servlet-name>
  		<url-pattern>*.obj</url-pattern>
    </servlet-mapping>
	```
  
5.	Creating new "Spring Bean Configuration file" inside "WEB-INF" folder
	
	filename: "spring.xml"
	
	Choose THREE namespaces: 
	
		1. beans
		2. mvc
		3. context

6.	Add following lines inside "spring.xml" file
		
	```xml
	<context:component-scan base-package="com.cg"/>
	<mvc:annotation-driven/>
	<mvc:view-resolvers>
		<mvc:jsp suffix=".jsp" prefix="/WEB-INF/pages/"  />
	</mvc:view-resolvers>
	```
7.	create new folder inside "WEB-INF" with name "pages"

8.	create new JSP page "hello" inside "WEB-INF/pages" folder

9.	Add following line inside "hello.jsp" page

		<h2>Hello ${user}, today is ${today}</h2>

10.	Create new Class `com.cg.controllers.HelloController`
	
	```java
	@Controller
	@RequestMapping("/hello.obj")
	public class HelloControllers {

	@GetMapping
	public ModelAndView process(){
			ModelAndView mv = new ModelAndView("hello");//VIEW
		mv.addObject("user", "Chris Evans");//MODEL
		mv.addObject("today", new Date());//MODEL
		return mv;
		}
	}

11.	Right click on Project > Run On Server 

12.	Try URL:

	> http://localhost:8080/webdemo1/hello.obj

