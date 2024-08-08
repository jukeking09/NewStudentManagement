package com.zach.NewStudentManagement.service;

import com.zach.NewStudentManagement.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zach.NewStudentManagement.model.Subject;
import com.zach.NewStudentManagement.repository.courseRepository;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class courseService {

    @Autowired
    private courseRepository courseRepository;

    // Create a new course
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return Optional.ofNullable(courseRepository.findById(id));
    }

    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.update(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Set<Subject> getSubjectsByCourseId(Long courseId) {
        return courseRepository.findSubjectsByCourseId(courseId);
    }
}
