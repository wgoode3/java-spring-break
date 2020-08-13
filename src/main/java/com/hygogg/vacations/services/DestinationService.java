package com.hygogg.vacations.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hygogg.vacations.models.Destination;
import com.hygogg.vacations.repositories.DestinationRepository;

@Service
public class DestinationService {

	private final DestinationRepository dr;
	
	public DestinationService(DestinationRepository dr) {
		this.dr = dr;
	}
	
	public Destination create(Destination d) {
		return dr.save(d);
	}
	
	public List<Destination> getAll() {
		return (List<Destination>) dr.findAll();
	}
	
}
