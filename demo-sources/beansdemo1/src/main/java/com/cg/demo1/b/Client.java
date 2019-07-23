package com.cg.demo1.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("demo1-B.xml");
		Department d = context.getBean(Department.class);
		System.out.println(d);
	}

}
