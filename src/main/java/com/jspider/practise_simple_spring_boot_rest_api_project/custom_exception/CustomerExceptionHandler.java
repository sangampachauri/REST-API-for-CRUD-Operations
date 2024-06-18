package com.jspider.practise_simple_spring_boot_rest_api_project.custom_exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jspider.practise_simple_spring_boot_rest_api_project.response.ApplicationResponse;

@ControllerAdvice
public class CustomerExceptionHandler {
	
	@Autowired
	private ApplicationResponse<String> applicationResponse;
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ApplicationResponse<String>> idNotFoundExceptionHandler(IdNotFoundException e){
		applicationResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		applicationResponse.setStatusMsg("Given Id not found");
		applicationResponse.setDescription("Please give valid Id");
		applicationResponse.setData(e.getMsg());
		return new ResponseEntity<ApplicationResponse<String>>(applicationResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ApplicationResponse<String>> customerAlreadyExistsExceptionHandler(CustomerAlreadyExistsException e){
		applicationResponse.setStatusCode(HttpStatus.CONFLICT.value());
		applicationResponse.setStatusMsg("Custumer already exist");
		applicationResponse.setDescription("Custumer trying to save is exist already, can not allow duplicate cutomer.");
		applicationResponse.setData(e.getMsg());
		return new ResponseEntity<ApplicationResponse<String>>(applicationResponse,HttpStatus.CONFLICT);
	}
	
}











