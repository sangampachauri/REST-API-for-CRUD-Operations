package com.jspider.practise_simple_spring_boot_rest_api_project.custom_exception;

public class CustomerAlreadyExistsException extends RuntimeException {
	
	private String msg;

	public CustomerAlreadyExistsException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
