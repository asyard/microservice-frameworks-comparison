package com.asy.test.micronautapp.person.core;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Produces
@Singleton
@Requires(classes = {Throwable.class})
@RequiredArgsConstructor
@Slf4j
public class WsExceptionHandler implements ExceptionHandler<Throwable, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, Throwable exception) {
        log.error("Exception : " + exception.getMessage(), exception);
        if (exception instanceof WsException wsException) {
            return HttpResponse.status(wsException.getInternalStatus(), wsException.getMessage());

        }
        return HttpResponse.serverError().body("server_error");
    }
}