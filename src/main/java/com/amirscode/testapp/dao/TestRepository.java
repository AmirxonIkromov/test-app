package com.amirscode.testapp.dao;

import com.amirscode.testapp.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findAllBySubjectIdAndLevel(Long subject_id, String level);
}
