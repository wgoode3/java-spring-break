package com.hygogg.vacations.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.vacations.models.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

	Optional<Country> findByName(String name);
	
}
