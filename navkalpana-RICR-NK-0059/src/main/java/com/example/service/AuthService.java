package com.example.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.model.Role;
import com.example.model.Teacher;
import com.example.repository.TeacherRepository;
import com.example.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Component
@Service
@RequiredArgsConstructor
public class AuthService {

    private final TeacherRepository teacherRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    
    public String register(RegisterRequest request) {

        if (teacherRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Teacher teacher = Teacher.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.TEACHER)
                .build();

        teacherRepository.save(teacher);

        return "Teacher Registered Successfully";
    }
    public LoginResponse login(LoginRequest request) {

        Teacher teacher = teacherRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), teacher.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(teacher);

        return new LoginResponse(
                token,
                teacher.getName(),
                teacher.getRole().name()
        );
    }
   
}





















