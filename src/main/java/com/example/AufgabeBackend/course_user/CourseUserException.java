package com.example.AufgabeBackend.course_user;

public class CourseUserException extends Exception{
    public CourseUserException(){

    }

    public CourseUserException(String errorMessage){
        super(errorMessage);
    }

    public CourseUserException(Throwable err){
        super(err);
    }

    public CourseUserException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }

}
