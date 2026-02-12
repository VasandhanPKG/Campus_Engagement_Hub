package com.example.event_management_system.repository;

import com.example.event_management_system.entity.Registration;
import com.example.event_management_system.entity.Event;
import com.example.event_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findByUserAndEvent(User user, Event event);

    List<Registration> findByUser(User user);

    List<Registration> findByEvent(Event event);

    long countByEvent(Event event);
}
