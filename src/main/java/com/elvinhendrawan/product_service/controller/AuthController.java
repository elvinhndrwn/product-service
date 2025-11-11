package com.elvinhendrawan.product_service.controller;

import com.elvinhendrawan.product_service.dto.AuthRequest;
import com.elvinhendrawan.product_service.dto.AuthResponse;
import com.elvinhendrawan.product_service.entity.User;
import com.elvinhendrawan.product_service.security.JwtUtil;
import com.elvinhendrawan.product_service.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest req) {
        logger.info("Register attempt for {}", req.getUsername());
        User u = userService.register(req.getUsername(), req.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(u.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        logger.info("Login attempt for {}", req.getUsername());
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        String token = jwtUtil.generateToken(req.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
