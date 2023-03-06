package com.example.hw30.service.exceptions;

/*
 *
 * @author Roman Netesa
 *
 */


/**
 * Загальний клас для помилок
 */
public class SolverException extends RuntimeException {

    public SolverException(String message) {
        super(message);
    }

    public SolverException(String message, Throwable e) {
        super(message, e);
    }

}