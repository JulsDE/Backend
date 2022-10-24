package com.example.AufgabeBackend.user;

import com.example.AufgabeBackend.course.Course;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    private String name;
    private String firstname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @ManyToMany(mappedBy = "assignedUsers")
    private Set<Course> assignedCourses = new HashSet<>();


    public User(){

    }

    public User(String name, String firstname, LocalDate dateOfBirth){
        this.name = name;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
    }

    public void assignToCourse(Course courseToAssign){
        this.assignedCourses.add(courseToAssign);
    }

    public void unassignFromCourse(Course courseToUnassign){
        if(this.assignedCourses.contains(courseToUnassign)) {
            this.assignedCourses.remove(courseToUnassign);
        }
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public Set<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public void setAssignedCourses(Set<Course> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }

/*    @Override
    public String toString(){
        return this.firstname + " " + this.name + " was born at the " + this.dateOfBirth;
    }*/

}
