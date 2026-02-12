package com.example.event_management_system.controller;

import com.example.event_management_system.entity.Event;
import com.example.event_management_system.service.EventService;
import com.example.event_management_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;

    // Create Event
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // Get All Events
//
//    public List<Event> getAllEvents() {
//        return eventService.getAllEvents();
//    }
    @GetMapping
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }


        // Get Event by ID
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // Update Event
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    // Delete Event
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully";
    }
}
