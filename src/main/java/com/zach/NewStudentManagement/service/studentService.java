package com.zach.NewStudentManagement.service;

import com.zach.NewStudentManagement.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zach.NewStudentManagement.model.Student;
import com.zach.NewStudentManagement.repository.studentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class studentService {

    @Autowired
    private studentRepository studentRepository;

    // Create a new student
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }
    public void updateStudent(Student student) {
        studentRepository.update(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.findByCourseId(courseId);
    }
}
