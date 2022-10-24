package com.example.AufgabeBackend.course_user;

import com.example.AufgabeBackend.course.Course;
import com.example.AufgabeBackend.course.CourseRepository;
import com.example.AufgabeBackend.user.User;
import com.example.AufgabeBackend.user.UserRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class CourseUserServiceTest2 {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    private CourseUserService courseUserService;

    @BeforeEach
    void setUp(){
        this.courseUserService = new CourseUserService(this.courseRepository, this.userRepository);
    }

    @Test
    @Transactional
    void testAddUserToCourse(){
        //given
        Long id = Long.valueOf(1);


        User userInstance = this.userRepository.findById(id).orElse(null);;

        this.courseUserService.addUserToCourse(id, userInstance);
        Set<User> users = courseUserService.getUserFromCourse(id);

        Assert.assertTrue(users != null && users.size() > 0);
        Assert.assertTrue(users.contains(userInstance));
    }
}