package com.example.AufgabeBackend.course_user;

import com.example.AufgabeBackend.course.Course;
import com.example.AufgabeBackend.course.CourseRepository;
import com.example.AufgabeBackend.user.User;
import com.example.AufgabeBackend.user.UserRepository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourseUserServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserRepository userRepository;

    private CourseUserService courseUserService;
    private static final Long ID = Long.valueOf(1);

    @BeforeEach
    void setUp() {
        this.courseUserService = new CourseUserService(courseRepository, userRepository);
    }


    @Test
    void testAddUserToCourse() {
        //given
        Course course = new Course(
                "English",
                "A course to learn english",
                "Language",
                LocalDate.of(2022,12, 12),
                LocalDate.of(2022, 12, 23)
        );
        course.setCourseId(ID);
        User user = new User(
                "Mustermann",
                "Max",
                LocalDate.of(2000, 02, 02)
        );
        user.setUserId(ID);

        given(courseRepository.findById(anyLong())).willReturn(Optional.of(course));
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));

        //when
        courseUserService.addUserToCourse(ID, user);

        //then
        verify(this.courseRepository).save(course);
        verify(this.userRepository).save(user);
        Assert.assertTrue(user.getAssignedCourses().contains(course));
        Assert.assertTrue(course.getAssignedUsers().contains(user));
    }
}