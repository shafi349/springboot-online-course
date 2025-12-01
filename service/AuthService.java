
package com.example.onlinecourse.service;

import com.example.onlinecourse.dto.AuthRequest;
import com.example.onlinecourse.dto.AuthResponse;
import com.example.onlinecourse.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    void register(RegisterRequest request);
}
