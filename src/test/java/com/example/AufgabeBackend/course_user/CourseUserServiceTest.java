package com.example.AufgabeBackend.course_user;

import com.example.AufgabeBackend.course.Course;
import com.example.AufgabeBackend.course.CourseRepository;
import com.example.AufgabeBackend.user.User;
import com.example.AufgabeBackend.user.UserRepository;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CourseUserServiceTest {

    private final static String HOST_IP = "http://localhost:8080/course/1/users";

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    private CourseUserService underTest;

    @BeforeEach
    public void setUp() {
        this.underTest = new CourseUserService(courseRepository, userRepository);
        Course course = new Course(
                "English",
                "A course to learn english",
                "Language",
                LocalDate.of(2022,12, 12),
                LocalDate.of(2022, 12, 23)
        );

        User user = new User(
                "Mustermann",
                "Max",
                LocalDate.of(1999, 01, 01)
        );
        this.courseRepository.save(course);
        this.userRepository.save(user);
    }

//    @AfterEach
//    public void tearDown() {
//        this.userRepository.deleteAll();
//        this.courseRepository.deleteAll();
//    }


    @Test
    @Transactional
    @Ignore
    void addUserToCourse() {
        //given
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(1));
        User userInstance = optionalUser.get();

        Optional<Course> optionalCourse = this.courseRepository.findById(Long.valueOf(1));
        Course courseInstance = optionalCourse.get();
        //when
        this.underTest.addUserToCourse(Long.valueOf(1), userInstance);


        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> userResponseEntity = testRestTemplate.getForEntity(HOST_IP, String.class);
        String stringResponseEntity = testRestTemplate.getForObject(HOST_IP, String.class);


        System.out.println(userResponseEntity);
        System.out.println(userInstance);
        System.out.println(courseInstance);
        System.out.println(courseInstance.getAssignedUsers());
        System.out.println(stringResponseEntity);


    }
}