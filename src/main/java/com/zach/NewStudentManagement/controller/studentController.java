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
    public String saveStudent(@ModelAttribute("student") Student student){

        studentService.createStudent(student);
        List<Student> students = studentService.getAllStudents();
        return "redirect:/createStudent";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student){

        studentService.updateStudent(student);
        List<Student> students = studentService.getAllStudents();
        return "redirect:/createStudent";
    }

    @GetMapping("/createStudent")
    public String createStudent(Model model) {
        try {
            System.out.println("CREATE");
            if (AuthenticationHelper.authenticated()) {
                System.out.println("AUTHENTICATED!");
            Student newStudent = new Student();
        List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("student", newStudent);
        model.addAttribute("newStudent", true);
        model.addAttribute("courses", courseList );
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("listStudents", students);
        return "StudentForm";}
        } catch (Exception e) {
            return "redirect:/login?logout";
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/subjectList/{course_id}")
    public ResponseEntity<Set<Subject>> getSubjectsByCourseId(@PathVariable("course_id") Long courseId) {
        try {
            System.out.println("inside");
//            Course course = courseService.getCourseById(courseId).get();
//            System.out.println("tostring:"+course.toString());
//            if (course == null) {
//                System.out.println("notFound");
//                return ResponseEntity.notFound().build();
//            }
            System.out.println(courseId);
            Set<Subject> subjects = courseService.getSubjectsByCourseId(courseId);
            System.out.println(subjects.toString());
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Optional<Student> student = Optional.of(studentService.getStudentById(id).get());
        model.addAttribute("student", student);
        model.addAttribute("courses", courseService.getAllCourses());
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("listStudents", students);
        model.addAttribute("newStudent", false);
        return "StudentForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/createStudent";
    }

    @GetMapping("/favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
        // Do nothing - just return an empty response to prevent 404 errors
    }
}
