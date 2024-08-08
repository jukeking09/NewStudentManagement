package com.zach.NewStudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zach.NewStudentManagement.model.Subject;
import com.zach.NewStudentManagement.repository.subjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class subjectService {

    @Autowired
    private subjectRepository subjectRepository;

    public List<Subject> getSubjectsByCourseId(Long courseId) {
        return subjectRepository.findByCourseId(courseId);
    }

    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}

