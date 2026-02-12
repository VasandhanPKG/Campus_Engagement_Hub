package com.example.event_management_system.repository;

import com.example.event_management_system.entity.EngagementScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface EngagementScoreRepository extends JpaRepository<EngagementScore, Long> {

    Page<EngagementScore> findAllByOrderByTotalPointsDesc(Pageable pageable);
}
