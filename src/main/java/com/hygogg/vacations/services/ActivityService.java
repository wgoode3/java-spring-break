package com.hygogg.vacations.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hygogg.vacations.models.Activity;
import com.hygogg.vacations.repositories.ActivityRepository;


@Service
public class ActivityService {

	private final ActivityRepository ar;
	
	public ActivityService(ActivityRepository ar) {
		this.ar = ar;
	}
	
	public Activity create(String name) {
		Optional<Activity> existing = ar.findByName(name); 
		return existing.isPresent() ? existing.get() : ar.save(new Activity(name));
	}
	
	public Activity getOne(Long id) {
		Optional<Activity> a = ar.findById(id);
		return a.isPresent() ? a.get() : null;
	}
	
	public List<Activity> getAll() {
		return (List<Activity>) ar.findAll();
	}
	
}
