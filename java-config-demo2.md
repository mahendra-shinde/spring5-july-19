## Java Configuration in Spring 3/4/5

Java Configuration with Annotations to make project more managable and easier.
No need to define individual bean inside AppConfig. Use @Component, @Repository, @Service & @Autowired directly inside the classes.

1. Create new maven project 
    ```yaml
    Name:       javaconfigdemo2
    Package:    com.cg
    artifact:   javaconfigdemo2
    packaging:  jar
    ```

2.  Open `pom.xml` file and add following dependency:

    ```xml
    <dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-context</artifactId>
  		<version>5.1.6.RELEASE</version>
  	</dependency>
    ```

3.  Create new Java class `com.cg.demo.EmployeeDAO`

    ```java   
    @Repository("dao")
    public class EmployeeDAO {
        
    }
    ```

4.  Create new Java class `com.cg.demo.EmployeeService`

    ```java
    @Service
    public class EmployeeService {
        private EmployeeDAO dao;
        public EmployeeDAO getDao() {
            return dao;
        }
        @Autowired
        public void setDao(EmployeeDAO dao) {
            this.dao = dao;
        }   
    }
    ```

5.  Create new Java class (Spring Configuration) `com.cg.app.AppConfig`

    ```java    
    @Configuration // This is REPLACEMENT of SPRING.XML
    @ComponentScan("com.cg.demo")
    public class AppConfig {
        //No Bean Definition needed!
    }
    ```
6.  Create new Java Class (With Main method) `com.cg.app.Client`

    ```java
    public class Client {

        public static void main(String[] args) {
            ApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class);
            EmployeeDAO dao = context.getBean("dao",EmployeeDAO.class);
            EmployeeService service = context.getBean(EmployeeService.class);
            
            System.out.println("dao: "+dao);
            System.out.println("service: "+service);
        }
    }
    ```