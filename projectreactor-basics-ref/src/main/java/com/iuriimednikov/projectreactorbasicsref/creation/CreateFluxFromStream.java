package com.iuriimednikov.projectreactorbasicsref.creation;

import java.util.List;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;

class CreateFluxFromStream {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        Stream<Integer> stream = numbers.stream().filter(n -> n %2 == 0);
        Flux<Integer> flux = Flux.fromStream(stream);
//        int first = flux.blockFirst();
//        int last = flux.blockLast();
//        System.out.println(first);
//        System.out.println(last);
        Iterable<Integer> iterable = flux.toIterable();
        System.out.println(iterable);
    }
}
