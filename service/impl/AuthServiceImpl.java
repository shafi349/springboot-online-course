
package com.example.onlinecourse.service.impl;

import com.example.onlinecourse.dto.AuthRequest;
import com.example.onlinecourse.dto.AuthResponse;
import com.example.onlinecourse.dto.RegisterRequest;
import com.example.onlinecourse.model.Role;
import com.example.onlinecourse.model.User;
import com.example.onlinecourse.repository.RoleRepository;
import com.example.onlinecourse.repository.UserRepository;
import com.example.onlinecourse.security.JwtUtil;
import com.example.onlinecourse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Username or email already exists");
        }
        User u = new User();
        u.setUsername(request.getUsername());
        u.setEmail(request.getEmail());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
        if (userRole == null) {
            userRole = new Role(null, "ROLE_USER");
            roleRepository.save(userRole);
        }
        roles.add(userRole);
        u.setRoles(roles);
        userRepository.save(u);
    }
}
