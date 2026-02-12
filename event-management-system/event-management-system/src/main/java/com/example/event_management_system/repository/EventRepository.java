package com.example.event_management_system.repository;

import com.example.event_management_system.entity.Event;
import com.example.event_management_system.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByEventType(EventType eventType, Pageable pageable);

    Page<Event> findByCategory_Name(String categoryName, Pageable pageable);
}
