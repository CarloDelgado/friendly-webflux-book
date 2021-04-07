package me.mednikov.projectreactorbasicsref.pipelines;

import me.mednikov.projectreactorbasicsref.services.StudentService;
import reactor.core.publisher.Mono;

class Pipelines {
    
    public static void main(String[] args) {
        StudentService service = new StudentService();
//        Mono<Double> result = service.findStudentByName("Aneta")
//                .flatMap(s -> Mono.just(s.getGpa()))
//                .doOnError(err -> System.out.println("Error occured!"))
//                .onErrorReturn(5.0)
//                .switchIfEmpty(Mono.just(4.0));
//        Double value = result.block();
//        System.out.println(value);
        try {
        Mono<Double> result = service.findStudentByName("Aneta")
                    .flatMap(s -> Mono.just(s.getGpa()))
                    .doOnError(err -> System.out.println("Error occured!"))
                    .onErrorReturn(5.0)
                    .switchIfEmpty(Mono.just(4.0));
            Double value = result.block();
            System.out.println(value);
        } catch (RuntimeException ex){
            System.out.println("Exception occured!");
        }
    }
    
}
