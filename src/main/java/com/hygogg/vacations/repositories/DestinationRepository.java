package com.hygogg.vacations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.vacations.models.Destination;


@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {}
