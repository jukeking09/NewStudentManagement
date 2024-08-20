//package com.zach.NewStudentManagement.controller;
//
//import ch.qos.logback.classic.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import com.zach.NewStudentManagement.model.Course;
//import com.zach.NewStudentManagement.service.courseService;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//public class testController {
//
//    @Autowired
//    courseService courseService;
//
//    @GetMapping("/{course_id}")
//    public ResponseEntity<Set<String>> getSubjectsByCourseId(@PathVariable("course_id") Long courseId) {
//        Logger logger = (Logger) LoggerFactory.getLogger(getClass());
//        try {
//            course course = courseService.getCourseById(courseId).get();
//
//            if (course == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            Set<String> cleanedSubjects = course.getSubjects().stream()
//                    .map(sub -> cleanTrailingCharacters(sub.toString()))
//                    .collect(Collectors.toSet());
//
//            return ResponseEntity.ok(cleanedSubjects);
//        } catch (Exception e) {
//            logger.error("Error fetching subjects for course " + courseId, e);
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    private String cleanTrailingCharacters(String input) {
//        // Remove all trailing '}' and ']' characters
//        while (input.endsWith("}") || input.endsWith("]")) {
//            input = input.substring(0, input.length() - 1);
//        }
//
//        // Add back a single ']}'
//        return input + "]}";
//    }
//}
