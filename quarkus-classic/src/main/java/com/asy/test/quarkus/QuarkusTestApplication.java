package com.asy.test.quarkus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain //By default, Quarkus will automatically generate a main method
public class QuarkusTestApplication {

    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
