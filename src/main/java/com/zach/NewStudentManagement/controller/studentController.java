package com.zach.NewStudentManagement.controller;

import ch.qos.logback.classic.Logger;
import com.zach.NewStudentManagement.helper.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.zach.NewStudentManagement.model.Course;
import com.zach.NewStudentManagement.model.Student;
import com.zach.NewStudentManagement.model.Subject;
import com.zach.NewStudentManagement.service.studentService;
import com.zach.NewStudentManagement.service.subjectService;
import com.zach.NewStudentManagement.service.courseService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class studentController {
    @Autowired
    studentService studentService;

    @Autowired
    courseService courseService;

    @Autowired
    subjectService subjectService;

    @PostMapping("/saveStudent")
    @ResponseBody
    public ResponseEntity<String> saveStudent(@ModelAttribute("student") Student student) {
        try {
            studentService.createStudent(student);
            return ResponseEntity.ok("Student saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving student");
        }
    }

    @PostMapping("/updateStudent")
    @ResponseBody
    public ResponseEntity<String> updateStudent(@ModelAttribute("student") Student student) {
        try {
            studentService.updateStudent(student);
            return ResponseEntity.ok("Student updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating student");
        }
    }


    @GetMapping("/createStudent")
    public String createStudent(Model model) {
        try {
            if (AuthenticationHelper.authenticated()) {
                Student newStudent = new Student();
                List<Course> courseList = courseService.getAllCourses();
                model.addAttribute("student", newStudent);
                model.addAttribute("courses", courseList );
                return "StudentForm";
            }
        } catch (Exception e) {
            return "redirect:/login?logout";
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/subjectList/{course_id}")
    public ResponseEntity<Set<Subject>> getSubjectsByCourseId(@PathVariable("course_id") Long courseId) {
        try {
            Set<Subject> subjects = courseService.getSubjectsByCourseId(courseId);
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/deleteStudentAjax/{id}")
    public ResponseEntity<String> deleteStudentAjax(@PathVariable Long id) {
        System.out.println("inside delete");
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @GetMapping("/favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
        // Do nothing - just return an empty response to prevent 404 errors
    }
    @GetMapping("/listStudentsAjax")
    public ResponseEntity<List<Student>> listStudentsAjax() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}
