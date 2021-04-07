package me.mednikov.projectreactorbasicsref.services;

import me.mednikov.projectreactorbasicsref.entities.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StudentService {
    
    public Flux<Student> findAllStudents(){
        return null;
    }
    
    public Mono<Student> findStudentByName(String name){
//        return null;
        throw new RuntimeException();
//        return Mono.error(new RuntimeException());
    }
}
