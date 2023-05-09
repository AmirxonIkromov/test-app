package com.amirscode.testapp.controller;

import com.amirscode.testapp.model.TestRequest;
import com.amirscode.testapp.service.TestService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;


    @GetMapping
    public ResponseEntity<?> getTestsByLevel(@RequestBody @NonNull TestRequest testRequest) {
        return testService.getTestsByLevel(testRequest);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addTest(@RequestBody TestRequest testRequest) {
        return testService.addTest(testRequest);
    }
}
