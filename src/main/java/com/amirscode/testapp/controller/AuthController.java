package com.amirscode.testapp.controller;

import com.amirscode.testapp.model.LoginRequest;
import com.amirscode.testapp.model.RegisterRequest;
import com.amirscode.testapp.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;


@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/hello")
    public String hello() {
        return "Hello from tomcat";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@NonNull @Email @RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@NonNull @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam Integer emailCode, @RequestParam String email) {
        return authService.verifyEmail(emailCode, email);
    }

}
