package com.iuriimednikov.projectreactorbasicsref.mapping;

import com.iuriimednikov.projectreactorbasicsref.entities.Student;
import reactor.core.publisher.Mono;

class ReactiveMapFlatMap {
    
    public static void main(String[] args) {
        Mono<Student> studentMono = Mono.just(new Student("Marketa", 3.5));
        Mono<String> nameMap = studentMono.map(student -> student.getName());
        Mono<String> nameFlatMap = studentMono.flatMap(student -> Mono.just(student.getName()));
        
        
    }
    
}
