package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.AssignmentSubmission;
import com.example.model.QuizAttempt;
import com.example.model.Student;
import com.example.model.StudentProgress;
import com.example.repository.AssignmentSubmissionRepository;
import com.example.repository.AttendanceRepository;
import com.example.repository.QuizAttemptRepository;
import com.example.repository.StudentProgressRepository;
import com.example.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentProgressService {

    private final AttendanceRepository attendanceRepository;
    private final AssignmentSubmissionRepository assignmentRepo;
    private final QuizAttemptRepository quizRepo;
    private final StudentRepository studentRepository;
    private final StudentProgressRepository progressRepository;

    public void updateProgress(Long studentId) {

        long total = attendanceRepository.count();
        long present = attendanceRepository.findAll()
                .stream()
                .filter(a -> a.getStudent().getId().equals(studentId)
                        && a.getStatus().name().equals("PRESENT"))
                .count();

        double attendance = total == 0 ? 0 : (present * 100.0) / total;

        double assignmentAvg = assignmentRepo.findAll().stream()
                .filter(a -> a.getStudent().getId().equals(studentId) && a.isEvaluated())
                .mapToDouble(AssignmentSubmission::getMarks)
                .average().orElse(0);

        double quizAvg = quizRepo.findAll().stream()
                .filter(q -> q.getStudent().getId().equals(studentId))
                .mapToDouble(QuizAttempt::getScore)
                .average().orElse(0);

        double ogi = (attendance * 0.3) + (assignmentAvg * 0.4) + (quizAvg * 0.3);

        Student student = studentRepository.findById(studentId).get();

        StudentProgress progress = progressRepository.findAll()
                .stream()
                .filter(p -> p.getStudent().getId().equals(studentId))
                .findFirst()
                .orElse(new StudentProgress());

        progress.setStudent(student);
        progress.setAttendancePercentage(attendance);
        progress.setAssignmentAverage(assignmentAvg);
        progress.setQuizAverage(quizAvg);
        progress.setOverallGrowthIndex(ogi);

        progressRepository.save(progress);
    }
}