package com.example.AufgabeBackend.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    private CourseService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new CourseService(courseRepository);
    }

    @Test
    void createCourse() {
        //given
        Course course = new Course(
                "English",
                "A course to learn english",
                "Language",
                LocalDate.of(2022,12, 12),
                LocalDate.of(2022, 12, 23)
        );

        //when
        this.underTest.createCourse(course);

        //then
        ArgumentCaptor<Course> courseArgumentCaptor = ArgumentCaptor.forClass(Course.class);

        verify(this.courseRepository).save(courseArgumentCaptor.capture());
        Course capturedCourse = courseArgumentCaptor.getValue();
        assertThat(capturedCourse).isEqualTo(course);
    }



}