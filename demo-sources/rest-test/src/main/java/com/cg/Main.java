package com.cg;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cg.entities.Country;

public class Main {

	
	private static final String BASE_URL = "http://localhost:5000/countries/";
	public static void main(String[] args) {

		RestTemplate template = new RestTemplate();
		Country country = template.getForObject(BASE_URL+"code/IN", Country.class);
		System.out.println("Found country: "+country.getName());
		
		Country cn = new Country("NN","Narnia","Antarctica","Wakad");
		ResponseEntity<String> response =  template.postForEntity(BASE_URL+"/new", cn, String.class);
		System.out.println("Response "+response.getBody());
	}

}
