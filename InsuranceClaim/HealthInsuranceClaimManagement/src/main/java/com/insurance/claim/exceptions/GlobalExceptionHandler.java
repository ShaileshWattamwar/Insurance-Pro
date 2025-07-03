package com.insurance.claim.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.insurance.claim.dto.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InsufficientCoverageException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientCoverage(
            InsufficientCoverageException ex, HttpServletRequest request) {
		
		ErrorResponse errorResponse = ErrorResponse.builder()
		.timestamp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.error("Insufficient Coverage")
		.message(ex.getMessage())
		.path(request.getRequestURI())
		.build();
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
}
