## Spring JPA Integration (Without Spring-boot)

1.  Create new maven project

    ```yaml
    Projectname:    jpa-demo1
    GroupId:        com.cg
    ArtifactId:     jpa-demo1
    Packaging:      War
    ```

2.  Right click on projectname > properties 

        In Java Build path, set JRE to JavaSE-1.8

        In Targeted Runtime, choose Tomcat 8 or Pivotal TC

3.  Open `pom.xml` add dependency: 

    1.  spring-webmvc:5.1.6.RELEASE
    2.  spring-orm:5.1.6.RELEASE
    3.  spring-tx:5.1.6.RELEASE
    4.  oracle6
    5.  jstl:1.2
    6.  hibernate-entitymanager:4.2.1.Final

    ```xml
    <dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-webmvc</artifactId>
  		<version>5.1.6.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-orm</artifactId>
  		<version>5.1.6.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>com.oracle</groupId>
  		<artifactId>ojdbc6</artifactId>
  		<version>11.2.0.3</version>
  	</dependency>
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>jstl</artifactId>
  		<version>1.2</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-tx</artifactId>
  		<version>5.1.6.RELEASE</version>
  	</dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>5.3.9.Final</version>
    </dependency>

    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.9</version>
        <type>jar</type>
    </dependency>
    ```

4.  Right click on projectname > Java EE Tools > Generate Deployment descriptor stub

5.  Goto `/src/main/webapp/WEB-INF/web.xml` file and add Spring Dispatcher Servlet

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
6.  Create Spring bean definition file `WEB-INF\spring.xml`

    ```xml
    	<context:component-scan base-package="com.cg"/>
	<mvc:annotation-driven/>
	<mvc:view-resolvers>
		<mvc:jsp suffix=".jsp" prefix="/WEB-INF/pages/"  />
	</mvc:view-resolvers>
	
	<bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
		<property name="driverClass" value="oracle.jdbc.OracleDriver"/>
		<property name="url" value="jdbc:oracle:thin:@localhost:1521/xe"/>
		<property name="username" value="hr"/>
		<property name="password" value="hr"/>
	</bean>
	
	<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="dataSource" ref="dataSource"/>
   		<property name="persistenceProviderClass" value="org.hibernate.jpa.HibernatePersistenceProvider"/>

		<property name="packagesToScan" value="com.cg.entities"/>
		<property name="jpaPropertyMap">
			<map>
				<entry key="hibernate.dialect" value="org.hibernate.dialect.Oracle10gDialect"/>
				<entry key="hibernate.hbm2ddl.auto" value="update"/>
			</map>
		</property>
	</bean>
	
	<bean id="tx" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory"/>
	</bean>
	
	<tx:annotation-driven transaction-manager="tx"/>
    ```

7.  Create new Entity class `com.cg.entities.Product`

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
8.  Create an Interface for DAO 'com.cg.dao.ProductDAO`

    ```java
    public interface ProductDAO {
        public void save(Product p);
        public Product findById(Integer productId);
        public List<Product> findAll();
    }
    ```
9.  Create a DAO Implementation class `com.cg.dao.ProductDAOImpl`

    ```java
    @Repository
    public class ProductDAOImpl implements ProductDAO {

        @PersistenceContext
        private EntityManager em;
        
        @Override
        public void save(Product p) {
            em.persist(p);
            em.flush();
        }

        @Override
        public Product findById(Integer productId) {
            return em.find(Product.class, productId);
        }

        @Override
        public List<Product> findAll() {
            Query q = em.createQuery("from Product p");//That's "JPQL" not SQL !!!
            return q.getResultList();
        }
    }
    ```
10. Create new Interface `com.cg.services.ProductService`

    ```java
    public interface ProductService {
        Product find(int productId);
        List<Product> getAll();
        void create(Product p);
    }
    ```

11. Create Service Implementation class `com.cg.services.ProductServiceImpl`

    ```java
    @Service
    @Transactional
    public class ProductServiceImpl implements ProductService {

        @Autowired private ProductDAO dao;
        
        @Transactional(readOnly=true)
        @Override
        public Product find(int productId) {
            return dao.findById(productId);
        }

        @Transactional(readOnly=true)
        @Override
        public List<Product> getAll() {
            return dao.findAll();
        }

        @Transactional(propagation=Propagation.REQUIRED)
        @Override
        public void create(Product p) {
            dao.save(p);
        }

    }
    ```

12. Create new Rest Controller `com.cg.controllers.ProductController`

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

13. Run Application on Tomcat 8 and Wait for browser window to open

    Once browser shows message "Three products created!" , verify record from sql-plus.
    Try URL /list to get all records