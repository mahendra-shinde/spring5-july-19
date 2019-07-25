## Spring Data-JPA with Spring Boot

1. Create new Maven project

    ```yaml
    Projectname:    data-jpa-demo
    Packaging:      JAR
    GroupId:        com.cg
    ArtifactId:     data-jpa-demo
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
    <dependency>
    	<groupId>com.oracle</groupId>
    	<artifactId>ojdbc6</artifactId>
    	<version>11.2.0.3</version>
    </dependency>
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    </dependencies>
    ```

3.  Create following packages.

    * com.cg
    * com.cg.controllers
    * com.cg.entities
    * com.cg.services
    * com.cg.daos

4.  Create new `application.properties` file inside `src/main/resources` directory.

    ```ini
    server.port=3000

    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
    spring.datasource.username=hr
    spring.datasource.password=hr
    spring.jpa.show-sql=true
    spring.jpa.database-platform=org.hibernate.dialect.Oracle10gDialect
    spring.jpa.hibernate.ddl-auto=create-drop
    ```

5.  Create new Application class `com.cg.Application`

    ```java
    @SpringBootApplication
    public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }
    ```
6.  Create new Entity class `com.cg.entities.Product`

    ```java
    import javax.persistence.*;

    @Entity
    @Table(name="products")
    public class Product {
        @Id
        private Integer productId;
        
        @Column(name="name",length=20)
        private String name;
        
        @Column(name="description",length=120)
        private String description;
        
        @Column(name="price")
        private Double price;
        //... constructor, getters & setters...
    }
    ```
7.  Create an Interface for DAO 'com.cg.daos.ProductDAO`

    ```java
    @Repository
    public interface ProductDAO  extends JpaRepository<Product, Integer>{
       
    }
    ```

8.  Create new Interface `com.cg.services.ProductService`

    ```java
    public interface ProductService {
        Product find(int productId);
        List<Product> getAll();
        void create(Product p);
    }
    ```

9.  Create Service implementation `com.cg.services.ProductServiceImpl`

    ```java
    @Service
    @Transactional
    public class ProductServiceImpl implements ProductService {

        @Autowired private ProductDAO dao;
        
        @Transactional(readOnly=true)
        @Override
        public Product find(int productId) {
            Optional<Product> product = dao.findById(productId);
            if(product.isPresent()) {
                return product.get();
            }
            else
                throw new RuntimeException("Product not found!");
        }

        @Transactional(readOnly=true)
        @Override
        public List<Product> getAll() {
            return dao.findAll();
        }

        @Transactional(propagation=Propagation.REQUIRED)
        @Override
        public void create(Product p) {
            if(dao.existsById(p.getProductId())) {
                throw new RuntimeException("Product Already exists!!");
            }
            dao.save(p);
        }
    }
    ```

10. Create new Rest Controller `com.cg.controllers.ProductController`

    ```java
    @RestController
    public class ProductController {
        
        @Autowired private ProductService service;
        
        @GetMapping("/")
        public String createSample() {
            Product p1 = new Product(101,"Windows 10 PRO","64Bit OS for Desktop & Laptops",8000D);
            Product p2 = new Product(102,"Ubuntu 18.04 Desktop","64Bit OS for Desktop & Laptops",0D);
            Product p3 = new Product(103,"Linux Mint 19","64Bit OS for Desktop & Laptops",0D);
            service.create(p1);
            service.create(p2);
            service.create(p3);
            return "Three products created!";
        }
        
        @GetMapping("/list")
        public List<Product> getAll(){
            return service.getAll();
        }
    }
    ```

11. Run Application on Tomcat 8 and Wait for browser window to open

    Once browser shows message "Three products created!" , verify record from sql-plus.
    Try URL /list to get all records

> Click [here](./demo-sources/data-jpa-demo) for complete source code