package com.iuriimednikov.customerserviceref.validation;

import reactor.core.publisher.Mono;

interface BaseValidator<T> {
    
    Mono<T> validate (T t);
    
}
