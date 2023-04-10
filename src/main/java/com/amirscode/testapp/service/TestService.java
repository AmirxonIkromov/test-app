package com.amirscode.testapp.service;

import com.amirscode.testapp.dao.TestRepository;
import com.amirscode.testapp.entity.Test;
import com.amirscode.testapp.model.TestRequest;
import com.amirscode.testapp.model.TestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;


    public ResponseEntity<?> getTestsByLevel(TestRequest testRequest) {

        List<Test> testList = testRepository.findAllBySubjectIdAndLevel(testRequest.getSubjectId(), testRequest.getLevel());
        List<TestResponse> testResponseList = new ArrayList<>();
        return ResponseEntity.ok(testList);
    }
}
