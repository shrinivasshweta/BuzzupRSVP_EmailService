package com.stackroute.buzzup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.buzzup.config.KafkaConfiguration;
import com.stackroute.buzzup.model.EventDetails;
import com.stackroute.buzzup.repository.EventRepository;

@Service
public class EventDetailsServiceImpl implements EventDetailsService {

	private EventRepository eventRepository;

	private String topic;
	topic=KafkaConfiguration.getTopic();

	// Kafka template from configuration and topic

	public KafkaTemplate<String, EventDetails> kafkaTemplate;

	
	@Autowired
	public EventDetailsServiceImpl(EventRepository eventRepository, KafkaTemplate<String, EventDetails> kafkaTemplate) {
		super();
		this.eventRepository = eventRepository;
		this.kafkaTemplate = kafkaTemplate;
	}

	//Method to create Event
	public EventDetails createEvent(EventDetails eventDetails) {
		kafkaTemplate.send(topic,eventDetails );
		EventDetails savedEvent = eventRepository.save(eventDetails);
		return savedEvent;
	}

	//Method to get event data by Event Name using Repository Method
	public EventDetails eventData(String eventName) {
		EventDetails eventData = eventRepository.getByEventName(eventName);
		return eventData;
	}

	//Method to get details of all the events
	public List<EventDetails> getDetails() {
		List<EventDetails> eventDetailsList = (List<EventDetails>) eventRepository.findAll();
		return eventDetailsList;
	}

/*	public List<EventDetails> getEvent(String emailId) {
		List<EventDetails> eventList = eventRepository.getByEmailId(emailId);
		return eventList;
	}*/

	//Method to get the event by emailId using repository method
	public EventDetails getEvent(String emailId) {
		EventDetails eventList = eventRepository.getByEmailId(emailId);
		return eventList;
	}
	
	//Method to delete the event by emailId
	public boolean deletedEvent(String emailId)
	{
		EventDetails deletedEvent = eventRepository.getByEmailId(emailId);
		eventRepository.delete(deletedEvent);
		return true;
	}
	
	public boolean deleteEvent(String id) {
		EventDetails ed = getUserById(id);
		if (ed != null) {
			eventRepository.delete(ed);
			return true;
		} else
			return false;

	}

	//Method to retrieve event using id
	private EventDetails getUserById(String id) {
		EventDetails displayEvent = eventRepository.findById(id).get();

		return displayEvent;
	}

}

/*
 * public EventDetails deleteEventbyName(String eventName) { EventDetails ed =
 * eventRepository.getByEventName(eventName); EventDetails deletedEvent =
 * eventRepository.delete(ed); return deletedEvent;
 * 
 * }
 * 
 * public EventDetails deleteEventByEmail(String emailId) { List<EventDetails>
 * ed = eventRepository.getByEmailId(emailId); EventDetails deletedEvent =
 * eventRepository.deleteById(ed); return deletedEvent;
 * 
 * }
 */
