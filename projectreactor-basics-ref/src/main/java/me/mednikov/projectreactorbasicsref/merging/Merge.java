package me.mednikov.projectreactorbasicsref.merging;

import java.time.Duration;
import reactor.core.publisher.Flux;

class Merge {
    
    public static void main(String[] args) {
//        Flux<Integer> num1 = Flux.just(1,3,5,7,9);
//        Flux<Integer> num2 = Flux.just(2,4,6,8,10);
//        
//        Flux<Integer> result = Flux.concat(
//                num1.delayElements(Duration.ofSeconds(2)),
//                num2.delayElements(Duration.ofSeconds(1))
//        );
//        
//        Iterable<Integer> iterable = result.toIterable();
//        iterable.forEach(System.out::println);
//
        Flux<Integer> num1 = Flux.just(1,3,5,7,9);
        Flux<Integer> num2 = Flux.just(2,4,6,8,10);
        
        Flux<Integer> result = Flux.merge(
                num1.delayElements(Duration.ofSeconds(2)),
                num2.delayElements(Duration.ofSeconds(1))
        );
        
        Iterable<Integer> iterable = result.toIterable();
        iterable.forEach(System.out::println);
    }
    
}
