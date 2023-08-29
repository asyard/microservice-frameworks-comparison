package com.asy.test.quarkus.person.core;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class WsExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        log.error("Exception : ", exception);
        if (exception instanceof WsException wsException) {
            return Response.status(wsException.getInternalStatus()).entity(wsException.getMessage()).build();

        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("server_error").build();

    }
}
