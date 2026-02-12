package com.example.event_management_system.service;

import com.example.event_management_system.entity.*;
import com.example.event_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EngagementScoreRepository engagementScoreRepository;

    public Registration registerForEvent(Long eventId, Long userId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (registrationRepository.findByUserAndEvent(user, event).isPresent()) {
            throw new RuntimeException("Already registered");
        }

        if (registrationRepository.countByEvent(event) >= event.getMaxParticipants()) {
            throw new RuntimeException("Event is full");
        }

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);
        registration.setStatus(RegistrationStatus.REGISTERED);

        return registrationRepository.save(registration);
    }

    public List<Registration> getRegistrationsByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return registrationRepository.findByEvent(event);
    }

    public List<Registration> getRegistrationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return registrationRepository.findByUser(user);
    }

    @Transactional
    public void markAttendance(Long registrationId) {

        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        if (registration.getStatus() == RegistrationStatus.ATTENDED) {
            throw new RuntimeException("Already marked as attended");
        }

        registration.setStatus(RegistrationStatus.ATTENDED);

        User user = registration.getUser();

        EngagementScore score = engagementScoreRepository.findById(user.getId())
                .orElseGet(() -> {
                    EngagementScore newScore = new EngagementScore();
                    newScore.setUser(user);
                    newScore.setTotalPoints(0);
                    return newScore;
                });

        score.setTotalPoints(score.getTotalPoints() + 10);

        engagementScoreRepository.save(score);
    }

    public void cancelRegistration(Long registrationId) {
        registrationRepository.deleteById(registrationId);
    }
}
