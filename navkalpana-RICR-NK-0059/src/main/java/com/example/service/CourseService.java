package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Course;
import com.example.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }
    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}