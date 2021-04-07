package me.mednikov.projectreactorbasicsref.mapping;

import me.mednikov.projectreactorbasicsref.entities.Student;
import reactor.core.publisher.Mono;

class ReactiveMapFlatMap {
    
    public static void main(String[] args) {
        Mono<Student> studentMono = Mono.just(new Student("Marketa", 3.5));
        Mono<String> nameMap = studentMono.map(student -> student.getName());
        Mono<String> nameFlatMap = studentMono.flatMap(student -> Mono.just(student.getName()));
        
        
    }
    
}
