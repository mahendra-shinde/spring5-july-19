package com.cg.demo1.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("demo1-A.xml");
		Department d = context.getBean(Department.class);
		System.out.println(d);
	}

}
