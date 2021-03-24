package com.iuriimednikov.projectreactorbasicsref.services;

import com.iuriimednikov.projectreactorbasicsref.entities.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StudentService {
    
    public Flux<Student> findAllStudents(){
        return null;
    }
    
    public Mono<Student> findStudentByName(String name){
//        return null;
//        throw new RuntimeException();
        return Mono.error(new RuntimeException());
    }
}
