package com.amirscode.testapp.service;

import com.amirscode.testapp.dao.SubjectRepository;
import com.amirscode.testapp.dao.TestRepository;
import com.amirscode.testapp.entity.Subject;
import com.amirscode.testapp.entity.Test;
import com.amirscode.testapp.enums.Level;
import com.amirscode.testapp.model.TestRequest;
import com.amirscode.testapp.model.TestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final SubjectRepository subjectRepository;


    public ResponseEntity<?> getTestsByLevel(TestRequest testRequest) {

        List<Test> testList = testRepository.findAllBySubjectIdAndLevel(testRequest.getSubjectId(), testRequest.getLevel());
        List<TestResponse> testResponseList = new ArrayList<>();
        return ResponseEntity.ok(testList);
    }

    public ResponseEntity<?> addTest(TestRequest testRequest) {

        Subject subject = subjectRepository.findById(testRequest.getSubjectId()).orElseThrow(IllegalArgumentException::new);

        Test test = Test.builder()
                .question(testRequest.getQuestion())
                .option1(testRequest.getOption1())
                .option2(testRequest.getOption2())
                .option3(testRequest.getOption3())
                .rightAnswer(testRequest.getRightAnswer())
                .level(Level.valueOf(testRequest.getLevel()))
                .subject(subject).build();
        testRepository.save(test);

        return ResponseEntity.status(HttpStatus.CREATED).body(test);
    }
}
