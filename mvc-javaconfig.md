### Spring MVC with Java Config

1.  Create new maven project

    ```yaml
    Project Name:   mvc-javaconfig
    Packaging:      war
    Package:        com.cg
    Artifact:       mvc-javaconfig
    ```

2.  Add new Maven Dependency in `pom.xml` file

    ```xml
    <dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-webmvc</artifactId>
  		<version>5.1.6.RELEASE</version>
  	</dependency>
    ```

3.  Goto Project Properties > Targeted Runtime > Choose Tomcat 8 or Pivotal Server

4.  Create new Java Class `com.cg.app.MyAppInitializer`

    ```java
    //This class is REPLACEMENT for web.xml
    public class MyInitializer implements WebApplicationInitializer{

        @Override
        public void onStartup(ServletContext container) throws ServletException {
            AnnotationConfigWebApplicationContext context = 
                    new AnnotationConfigWebApplicationContext();
       		context.setServletContext(container);

            context.register(AppConfig.class);
            container.addListener(new ContextLoaderListener());	
            System.out.println("Initializing the Web Context");
		    ServletRegistration.Dynamic dispatcher = container.addServlet(
					"dispatcher",new DispatcherServlet(context)
		    );
		    dispatcher.setLoadOnStartup(1);
		    dispatcher.addMapping("/");
        }
        
    }
    ```

5.  Create new Java Configuration class `com.cg.app.AppConfig`

    ```java
    @Configuration
    @ComponentScan("com.cg")
    @EnableWebMvc
    public class AppConfig implements WebMvcConfigurer {

        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver view = new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
            view.setViewClass(JstlView.class);
            return view;
        }
    }
    ```
6.  Create a Controller class `com.cg.app.HomeController`

    ```java
    @Controller
    public class HomeController {
        
        @GetMapping("/")
        public String hello() {
            return "hello";
        }
    }
    ```
7.  Create new JSP page in `webapp/WEB-INF/pages` directory
    NOTE: Manually create WEB-INF/pages inside /src/main/webapp directory

    ```html
    <h2>Hello World!</h2>
    ```    

8.  Run on Server (Tomcat 8 or Pivotal TC 3)

NOTE:   Project would continue to show ERROR in pom.xml `web.xml missing` please ignore it!

