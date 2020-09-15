package com.example.maven;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.api.Test;

@Retention(RetentionPolicy.RUNTIME)
@Test
public @interface FastTest {

}
