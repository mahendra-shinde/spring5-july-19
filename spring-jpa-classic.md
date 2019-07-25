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
		<property name="driver" value="oracle.jdbc.OracleDriver"/>
		<property name="url" value="jdbc:oracle:thin:@localhost:1521/xe"/>
		<property name="username" value="hr"/>
		<property name="password" value="hr"/>
	</bean>
	
	<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="dataSource" ref="dataSource"/>
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

