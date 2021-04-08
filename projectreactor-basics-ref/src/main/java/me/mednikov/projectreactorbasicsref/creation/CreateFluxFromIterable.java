package me.mednikov.projectreactorbasicsref.creation;

import me.mednikov.projectreactorbasicsref.entities.Student;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class CreateFluxFromIterable {
    
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Aneta", 3.8),
                new Student("Barbora", 3.5),
                new Student("Carolina", 3.4),
                new Student("Denisa", 2.9),
                new Student("Eva", 2.9)
        );
        
        Flux<Student> flux = Flux.fromIterable(students);
        
//        Student first = flux.blockFirst();
//        Student last = flux.blockLast();
//        
//        System.out.println(first.getName());
//        System.out.println(last.getName());
        
//        Mono<Student> mono = flux.elementAt(2);
//        Student student = mono.block();
//        System.out.println(student.getName());
        
        Iterable<Student> iterable = flux.toIterable();
    }
}
