package com.jspider.practise_simple_spring_boot_rest_api_project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	private int id;
	private String name;
	private String email;
	private long phone;
}
