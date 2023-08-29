package com.asy.test.micronautapp.person.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WsException extends RuntimeException {
    private int internalStatus;
    private String message;
}
