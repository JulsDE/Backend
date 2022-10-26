package com.example.AufgabeBackend.course_user;

import com.example.AufgabeBackend.course.Course;
import com.example.AufgabeBackend.course.CourseRepository;
import com.example.AufgabeBackend.user.User;
import com.example.AufgabeBackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Service
public class CourseUserService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private Optional<Course> optionalCourse;
    private Optional<User> optionalUser;



    @Autowired
    public CourseUserService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    public void addUserToCourse(Long courseId, User userToAssign) {
        if(isCoursePresent(courseId) && isUserPresent(userToAssign.getUserId())) {
            assignmentManagement(true);
        }
    }

    public void unassignUserFromCourse(Long courseId, User userToDelete) {
        if(isCoursePresent(courseId) && isUserPresent(userToDelete.getUserId())) {
            assignmentManagement(false);
        }
    }

    public void assignmentManagement(boolean toAssign) {
        Course courseInstance = this.optionalCourse.get();
        User userInstance = this.optionalUser.get();
        if(toAssign){
            courseInstance.assignUser(userInstance);
            userInstance.assignToCourse(courseInstance);
        }
        else{
            courseInstance.unassignUser(userInstance);
            userInstance.unassignFromCourse(courseInstance);
        }
        courseRepository.save(courseInstance);
        userRepository.save(userInstance);
    }

    public Set<Course> getCoursesFromUser(Long userId) {
        if(isUserPresent(userId)){
            User user = this.optionalUser.get();
            return user.getAssignedCourses();
        }
        return null;
    }

    public Set<User> getUserFromCourse(Long courseId) {
        if(isCoursePresent(courseId)){
            Course course = this.optionalCourse.get();
            return course.getAssignedUsers();
        }
        return null;
    }




    public boolean isCoursePresent(Long courseId) {
        this.optionalCourse = courseRepository.findById(courseId);
        if(this.optionalCourse.isPresent()){
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with id "+ courseId + " not found");
    }

    public boolean isUserPresent(Long userId) {
        this.optionalUser = userRepository.findById(userId);
        if(this.optionalUser.isPresent()){
           return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id "+ userId + " not found");
    }

}
