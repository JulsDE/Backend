package com.example.AufgabeBackend.course;

import com.example.AufgabeBackend.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long courseId;


    private String title;
    private String description;
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="courses_users",
            joinColumns = @JoinColumn(name = "courseId", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "userId", nullable = false)
    )
    private Set<User> assignedUsers = new HashSet<>();


    public Course() {

    }

    public Course(String title, String description, String category, LocalDate start, LocalDate end) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.start = start;
        this.end = end;
    }


    public void assignUser(User userToAssign){
        this.assignedUsers.add(userToAssign);
    }

    public void unassignUser(User userToUnassign) {
        if (this.assignedUsers.contains(userToUnassign)) {
            this.assignedUsers.remove(userToUnassign);
        }
    }


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Set<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    /*@Override
    public String toString(){
        return "Title of Course: " + this.title + "\n" +
                "Description: " + this.description + "\n" +
                "Category: " + this.category + "\n" +
                "Start: " + this.start + "\n" +
                "End: " + this.end ;
    }*/
}
