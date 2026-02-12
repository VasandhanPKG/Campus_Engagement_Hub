package com.example.event_management_system.Controller;

import com.example.event_management_system.entity.Registration;
import com.example.event_management_system.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // Register Student to Event
    @PostMapping
    public Registration registerStudent(@RequestParam Long studentId,
                                        @RequestParam Long eventId) {
        return registrationService.registerStudent(studentId, eventId);
    }

    // Get All Registrations
    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    // Get Registrations by Event
    @GetMapping("/event/{eventId}")
    public List<Registration> getByEvent(@PathVariable Long eventId) {
        return registrationService.getRegistrationsByEvent(eventId);
    }

    // Get Registrations by Student
    @GetMapping("/student/{studentId}")
    public List<Registration> getByStudent(@PathVariable Long studentId) {
        return registrationService.getRegistrationsByStudent(studentId);
    }
}
