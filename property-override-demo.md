## Property Overrider Demo


1. Copy IOCDemo1 into postprocessordemo
2. Create person.properties inside /src/main/resource

    ```ini
    emp.firstName=Donald
    emp.lastName=Trumph
    address.city=pimpri-chinchwad
    ```

3.  Modify "spring.xml" file

    ```xml
    <context:property-override location="classpath:/person.properties"  />
    <bean id="emp" class="com.cg.iocdemo1.Employee" autowire="byType">
	<property name="firstName" value="${emp.fname}" />
		<property name="lastName" value="${emp.lname}" />
		<property name="designation" value="POTUS" />
		<property name="salary" value="1239990" />
		<property name="contacts">
			<list>
				<value>2369869879</value>
				<value>5765765677</value>
			</list>
		</property>
	</bean>
	
	<bean id="address" class="com.cg.iocdemo1.Address">
		<constructor-arg value="White house" />
		<constructor-arg value="Washignton DC" />
		<constructor-arg value="${address.city}" />
	</bean>

    ```

