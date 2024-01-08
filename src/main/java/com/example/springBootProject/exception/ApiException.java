package com.example.springBootProject.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 *  Record for returning information after specific exception thrown.
 * @param message: message for user,
 * @param httpStatus: http status code,
 * @param timestamp: current time stamp
 */

public record ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
}
