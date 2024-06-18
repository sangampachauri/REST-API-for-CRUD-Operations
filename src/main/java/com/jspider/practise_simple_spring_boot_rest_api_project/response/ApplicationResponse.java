package com.jspider.practise_simple_spring_boot_rest_api_project.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ApplicationResponse<T> {
	private int statusCode;
	private String statusMsg;
	private String description;
	private T data;

}
