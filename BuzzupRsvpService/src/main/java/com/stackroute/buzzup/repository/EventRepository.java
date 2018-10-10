package com.stackroute.buzzup.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.buzzup.model.EventDetails;
//This repository is used to perform various operations on MongoDB	

@Repository
public interface EventRepository extends MongoRepository<EventDetails, String> {

	public EventDetails getByEventName(String eventName);
	public EventDetails getByEmailId(String emailId);
	
}
