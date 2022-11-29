package com.zensar.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zensar.exception.EmptyStudentList;
import com.zensar.exception.StudentNotFound;

@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(StudentNotFound.class)
	public ResponseEntity<String> studentNotFound(StudentNotFound exception) {
		return new ResponseEntity<String>("Student is not available, please check again your Database",
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyStudentList.class)
	public ResponseEntity<String> emptyCouponList(EmptyStudentList exception) {
		return new ResponseEntity<String>("Empty Student List", HttpStatus.BAD_REQUEST);
	}
}
