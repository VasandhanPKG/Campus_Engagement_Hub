package com.example.event_management_system.service;

import com.example.event_management_system.entity.EngagementScore;
import com.example.event_management_system.repository.EngagementScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class LeaderboardService {

    @Autowired
    private EngagementScoreRepository engagementScoreRepository;

    public Page<EngagementScore> getLeaderboard(Pageable pageable) {
        return engagementScoreRepository.findAllByOrderByTotalPointsDesc(pageable);
    }
}
