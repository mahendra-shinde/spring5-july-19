## Creating Spring MVC Project (As Maven Project)


1. Create new Maven project 
   
	```yml
	Name: 		webdemo2
	Packaging: 	WAR
	GroupId: 	com.cg
	ArtifactId: 	webdemo2
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
  		<url-pattern>/</url-pattern>
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

9.	Add following line inside "home.jsp" page

	```html
		<h1>Welcome to Home page!</h1>
		${msg }
	```

10.	Create new Class `com.cg.controllers.HomeController`
	
	```java
	@Controller
	@RequestMapping("/")
	public class HomeController {

		/* Request Handling Method
		* 	Syntax:
		* 	1. public ModelAndView methodName(){ }
		*  2. public String methodName(Model model) { }
		*  3. public String methodName(Map<String,Object> model) { }
		*  4. public String methodName(){ }
		*/
		@GetMapping
		public String home(Model model) {
			model.addAttribute("msg","Welcome to Spring webmvc");
			return "home"; // Translated into /WEB-INF/pages/home.jsp
		}
	}

	```

11.	Right click on Project > Run On Server 

12.	Try URL:

	> http://localhost:8080/webdemo2

13.	Create additional Controller class `com.cg.controllers.SearchController`

	```java
	@Controller
	public class SearchController {
		
		@RequestMapping(value="/search",method=RequestMethod.GET)
		public String search(Model model, @RequestParam("q") String query) {
			System.out.println("Searching for "+query);
			model.addAttribute("msg","Product "+query
						+" is currently unavailable, please try later!");
			return "home";
		}
	}
	```

14.	Modify the existing `home.jsp` page inside `WEB-INF/pages` directory,
	add form to perform search:

	```html
	<h1>Welcome to Home page!</h1>
	${msg }
	<form action="search" method="get">
	  Search product : <input name="q" type="text"/>
	  <input type="submit" value="search"/>
	</form>
	```

		