package com.example.AufgabeBackend.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/course")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping
    public void createCourse(@RequestBody Course newCourse){
        courseService.createCourse(newCourse);
    }

    @PutMapping(path = "/{courseId}")
    public void updateCourse(@PathVariable Long courseId, Course courseUpdate){
        try {
            courseService.updateCourse(courseId, courseUpdate);
        }
        catch(CourseException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @DeleteMapping("{courseId}")
    public void deleteCourse(@PathVariable Long courseId){
        try {
            this.courseService.deleteCourse(courseId);
        }
        catch(CourseException exception) {
            System.out.println(exception);
        }
    }

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable Long courseId){
        return courseService.getCourse(courseId);
    }



}
