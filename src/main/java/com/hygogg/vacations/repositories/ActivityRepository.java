package com.hygogg.vacations.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.vacations.models.Activity;


@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
	
	Optional<Activity> findByName(String name);

}
