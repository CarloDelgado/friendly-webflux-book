package com.iuriimednikov.projectreactorbasicsref.merging;

import com.iuriimednikov.projectreactorbasicsref.entities.Student;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

class Zips {
    
    public static void main(String[] args) {
        Mono<String> nameMono = Mono.just("Veronika");
        Mono<Double> gpaMono = Mono.just(3.5);
        Mono<Tuple2<String, Double>> zippedMono = nameMono.zipWith(gpaMono);
        Mono<Student> studentMono = zippedMono.map(t -> new Student(t.getT1(), t.getT2()));
        Student result = studentMono.block();
        System.out.println(result.getName() + " " + result.getGpa());
    }
    
}
