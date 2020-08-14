package com.hygogg.vacations.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Optional<Country> existing = cr.findByName(c.getName());
		return existing.isPresent() ? null : cr.save(c);
	}
	
	public List<Country> getAll() {
		return (List<Country>) cr.findAll();
	}
	
	public Country getOne(Long id) {
		Optional<Country> c = cr.findById(id);
		return c.isPresent() ? c.get() : null;
	}
	
	public ArrayList<Country> topCountries() {
		List<Country> all = this.getAll();
		ArrayList<Country> c = new ArrayList<Country>();
		while(all.size() > 0) {
			Country temp = all.get(0);
			for(int i=1; i<all.size(); i++) {
				if(all.get(i).getDestinations().size() > temp.getDestinations().size()) {
					temp = all.get(i);
				}
			}
			c.add(temp);
			all.remove(temp);			
		}
		return c;
	}
	
}
