package com.hygogg.vacations.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hygogg.vacations.models.Country;
import com.hygogg.vacations.repositories.CountryRepository;

@Service
public class CountryService {

	private final CountryRepository cr;
	
	public CountryService(CountryRepository cr) {
		this.cr = cr;
	}
	
	public Country create(Country c) {
		return cr.save(c);
	}
	
	public List<Country> getAll() {
		return (List<Country>) cr.findAll();
	}
	
}
