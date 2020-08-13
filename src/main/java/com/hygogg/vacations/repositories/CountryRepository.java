package com.hygogg.vacations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.vacations.models.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {}
