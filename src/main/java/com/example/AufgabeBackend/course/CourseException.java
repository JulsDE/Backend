package com.example.AufgabeBackend.course;

public class CourseException extends Exception{

    public CourseException(){

    }

    public CourseException(String errorMessage){
        super(errorMessage);
    }

    public CourseException(Throwable err){
         super(err);
    }

    public CourseException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
