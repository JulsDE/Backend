package com.example.AufgabeBackend.course_user;


import com.example.AufgabeBackend.course.Course;
import com.example.AufgabeBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class CourseUserController {

    private final CourseUserService courseUserService;

    @Autowired
    public CourseUserController(CourseUserService courseUserService){
        this.courseUserService = courseUserService;
    }

    @PostMapping("/course/{courseId}/users")
    public void addUserToCourse(@PathVariable Long courseId, @RequestBody User userToAssign) {
        this.courseUserService.addUserToCourse(courseId, userToAssign);
    }

    @DeleteMapping("/course/{courseId}/users")
    public void unassignUserFromCourse(@PathVariable Long courseId, @RequestBody User userToDelete) {
        this.courseUserService.unassignUserFromCourse(courseId, userToDelete);
    }

    @GetMapping("/user/{userId}/course")
    public Set<Course> getCoursesFromUser(@PathVariable Long userId) {
        return this.courseUserService.getCoursesFromUser(userId);
    }


    @GetMapping("/course/{courseId}/users")
    public Set<User> getUsersFromCourse(@PathVariable Long courseId) {
        return this.courseUserService.getUserFromCourse(courseId);
    }
}
