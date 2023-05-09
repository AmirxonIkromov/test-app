package com.amirscode.testapp.controller;

import com.amirscode.testapp.model.SubjectDTO;
import com.amirscode.testapp.service.SubjectService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody @NonNull SubjectDTO subjectDTO) {
        return subjectService.add(subjectDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return subjectService.getAll();
    }

    @GetMapping("/{subId}")
    public ResponseEntity<?> get(@PathVariable Long subId) {
        return subjectService.get(subId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{subId}")
    public ResponseEntity<?> edit(@PathVariable Long subId, @RequestParam String subject) {
        return subjectService.edit(subId, subject);
    }
}
