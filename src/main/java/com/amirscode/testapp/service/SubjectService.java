package com.amirscode.testapp.service;

import com.amirscode.testapp.dao.SubjectRepository;
import com.amirscode.testapp.entity.Subject;
import com.amirscode.testapp.model.SubjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;


    public ResponseEntity<?> add(SubjectDTO subjectDTO) {
        Subject subject = Subject.builder()
                .name(subjectDTO.getName())
                .build();
        subjectRepository.save(subject);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }

    public ResponseEntity<?> get(Long subId) {

        Subject subject = subjectRepository.findById(subId).orElseThrow(NoSuchElementException::new);
        return ResponseEntity.ok(subject);
    }

    public ResponseEntity<?> edit(Long subId, String subject) {

        Subject oldSubject = subjectRepository.findById(subId).orElseThrow(NoSuchElementException::new);
        oldSubject.setName(subject);
        subjectRepository.save(oldSubject);
        return ResponseEntity.ok().build();
    }

}
