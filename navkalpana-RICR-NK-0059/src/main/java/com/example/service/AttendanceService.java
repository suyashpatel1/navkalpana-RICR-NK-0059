
package com.example.service;

import com.example.dto.AttendanceRequest;
import com.example.model.Attendance;
import com.example.model.AttendanceStatus;
import com.example.model.Student;
import com.example.model.Batch;
import com.example.repository.AttendanceRepository;
import com.example.repository.StudentRepository;
import com.example.repository.BatchRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;

    public void mark(AttendanceRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Batch batch = batchRepository.findById(request.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        AttendanceStatus status;
        try {
            status = AttendanceStatus.valueOf(request.getStatus().toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid Attendance Status");
        }

        Attendance attendance = Attendance.builder()
                .student(student)
                .batch(batch)
                .date(request.getDate())
                .status(status)
                .remarks(request.getRemarks())
                .build();

        attendanceRepository.save(attendance);
    }

    // ✅ Used in Controller → service.calculateAttendancePercentage()
    public double calculateAttendancePercentage(Long studentId) {

        long total = attendanceRepository.countByStudentId(studentId);

        if (total == 0) return 0;

        long present = attendanceRepository
                .countByStudentIdAndStatus(studentId, AttendanceStatus.PRESENT);

        return (double) present / total * 100;
    }

    // ✅ Used in Controller → service.getCountByStatus()
    public long getCountByStatus(Long studentId, AttendanceStatus status) {

        return attendanceRepository
                .countByStudentIdAndStatus(studentId, status);
    }
}