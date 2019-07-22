package com.cg.iocdemo1;

public class Main1 {
	public static void main(String[] args) {
		//Values/References assigned using Constructor,
		// Then it's constructor injection
		Address address = new Address("Rajiv Gandhi IT Park","Hinjewadi","Pune");
		Employee emp = new Employee();
		
		//Values/References assigned using Setter Method
		// Example of Setter Injection
		emp.setFirstName("Rajiv");
		emp.setLastName("Bhatia");
		emp.setAddress(address);
		emp.setDesignation("Actor");
		emp.setSalary(1200000);
		
		
		
	}
}
