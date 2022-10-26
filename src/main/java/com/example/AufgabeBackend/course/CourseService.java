package com.example.AufgabeBackend.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private Optional<Course> optionalCourse;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public void createCourse(Course newCourse){
        courseRepository.save(newCourse);
    }

    public void updateCourse(Long courseId, Course courseUpdate) {
        if(!isCoursePresent(courseId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course could not be update");
        }
        Course courseInstance = this.optionalCourse.get();
        updateCourseProperties(courseInstance, courseUpdate);
        courseRepository.save(courseInstance);
    }

    private void updateCourseProperties(Course courseToBeUpdated, Course courseUpdate) {
        courseToBeUpdated.setTitle(courseUpdate.getTitle());
        courseToBeUpdated.setDescription(courseUpdate.getDescription());
        courseToBeUpdated.setCategory(courseUpdate.getCategory());
        courseToBeUpdated.setStart(courseUpdate.getStart());
        courseToBeUpdated.setEnd(courseUpdate.getEnd());
    }

    public void deleteCourse(Long courseId) {
        if(isCoursePresent(courseId)) {
            courseRepository.deleteById(courseId);
        }
    }

    public Course getCourse(Long courseId) {
        if(isCoursePresent(courseId)) {
            return this.optionalCourse.get();
        }
        return null;
    }

    private boolean isCoursePresent(Long courseId) {
        this.optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()) {
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with the id " + courseId + " was not found");
    }

}
