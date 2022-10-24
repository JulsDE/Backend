package com.example.AufgabeBackend.course_user;

import com.example.AufgabeBackend.course.Course;
import com.example.AufgabeBackend.course.CourseRepository;
import com.example.AufgabeBackend.user.User;
import com.example.AufgabeBackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        try{
            if(isCoursePresent(courseId) && isUserPresent(userToAssign.getUserId())) {
                assignmentManagement(true);
            }
        }
        catch(CourseUserException exception){
            logException(exception);
        }
    }

    public void unassignUserFromCourse(Long courseId, User userToDelete) {
        try{
            if(isCoursePresent(courseId) && isUserPresent(userToDelete.getUserId())) {
                assignmentManagement(false);
            }
        }
        catch(CourseUserException exception){
            logException(exception);
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
        try{
            if(isUserPresent(userId)){
                User user = this.optionalUser.get();
                return user.getAssignedCourses();
            }
        }
        catch(CourseUserException exception){
            logException(exception);
        }
        return null;
    }

    public Set<User> getUserFromCourse(Long courseId) {
        try{
            if(isCoursePresent(courseId)){
                Course course = this.optionalCourse.get();
                return course.getAssignedUsers();
            }
        }
        catch(CourseUserException exception){
            logException(exception);
        }
        return null;
    }


    public boolean isCoursePresent(Long courseId) throws CourseUserException {
        this.optionalCourse = courseRepository.findById(courseId);
        if(!this.optionalCourse.isPresent()){
            throw new CourseUserException("Course with id " + courseId + " was not found");
        }
        return true;
    }

    public boolean isUserPresent(Long userId) throws CourseUserException {
        this.optionalUser = userRepository.findById(userId);
        if(!this.optionalUser.isPresent()){
            throw new CourseUserException("User with id " + userId + " was not found");
        }
        return true;
    }


    public void logException(Exception e){
        System.out.println(e.getMessage());
    }

}
