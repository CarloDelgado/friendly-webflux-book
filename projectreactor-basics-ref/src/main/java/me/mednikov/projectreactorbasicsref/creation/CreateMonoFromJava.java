package me.mednikov.projectreactorbasicsref.creation;

import me.mednikov.projectreactorbasicsref.entities.Student;
import java.util.Optional;
import reactor.core.publisher.Mono;

class CreateMonoFromJava {
    
    public static void main(String[] args) {
        Mono<Student> mono = Mono.justOrEmpty(null);
        Optional<Student> result = mono.blockOptional();
        System.out.println("Student name is: " + result.get().getName());
    }
}
