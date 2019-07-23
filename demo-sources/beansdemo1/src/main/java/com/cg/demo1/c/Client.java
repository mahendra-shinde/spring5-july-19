package com.cg.demo1.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("demo1-C.xml");
		Location location = context.getBean(Location.class);
		System.out.println(location);
	}

}
