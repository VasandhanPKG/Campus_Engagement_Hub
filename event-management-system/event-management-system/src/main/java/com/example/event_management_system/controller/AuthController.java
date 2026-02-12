package com.example.event_management_system.controller;

import com.example.event_management_system.entity.Student;
import com.example.event_management_system.repository.StudentRepository;
import com.example.event_management_system.dto.AuthRequest;
import com.example.event_management_system.dto.AuthResponse;
import com.example.event_management_system.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        if (student.getRole() == null) {
            student.setRole("ROLE_STUDENT");
        }
        studentRepository.save(student);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(student.getEmail(), student.getRole());

        return new AuthResponse(token);
    }
}
