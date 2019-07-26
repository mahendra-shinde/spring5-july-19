package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.daos.CountryDAO;
import com.cg.entities.Country;

@Service
public class CountryService {

	@Autowired private CountryDAO dao;
	
	public List<Country> byContinent(String continent){
		return dao.findByContinent(continent);
	}
	
	public Country byCode(String code) {
		return dao.findByCode(code);
	}
	
	public void create(Country country) {
		Country temp = dao.findByCode(country.getCode());
		if(temp==null)
		{
			dao.save(country);
		}else
			throw new RuntimeException("Country "+country.getCode()+" already exists!");
	}
	
	public void update(Country country) {
		Country temp = dao.findByCode(country.getCode());
		if(temp!=null)
		{
			dao.update(country);
		}else //TODO: Please throw an Exception is record DO NOT exists
			System.out.println("Country does not exists!");
	}
}
