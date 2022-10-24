package com.example.AufgabeBackend.course_user;

import com.example.AufgabeBackend.course.CourseRepository;
import com.example.AufgabeBackend.user.User;
import com.example.AufgabeBackend.user.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
class CourseUserServiceTest {

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
    void testAddUserToCourse() {
        //given
        Long id = Long.valueOf(1);
        User userInstance = this.userRepository.findById(id).orElse(null);;
        //when
        this.courseUserService.addUserToCourse(id, userInstance);
        //then
        Set<User> users = courseUserService.getUserFromCourse(id);
        Assert.assertTrue(users != null && users.size() > 0);
        Assert.assertTrue(users.contains(userInstance));
    }
}