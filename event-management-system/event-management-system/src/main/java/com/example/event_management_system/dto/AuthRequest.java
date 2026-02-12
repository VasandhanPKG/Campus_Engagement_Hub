package com.example.event_management_system.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}

