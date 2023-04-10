package com.amirscode.testapp.dao;

import com.amirscode.testapp.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
