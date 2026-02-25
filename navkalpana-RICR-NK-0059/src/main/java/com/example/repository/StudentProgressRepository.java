
package com.example.repository;

import com.example.model.StudentProgress;
import com.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentProgressRepository extends JpaRepository<StudentProgress, Long> {

    Optional<StudentProgress> findByStudent(Student student);

    Optional<StudentProgress> findByStudentId(Long studentId);
}
