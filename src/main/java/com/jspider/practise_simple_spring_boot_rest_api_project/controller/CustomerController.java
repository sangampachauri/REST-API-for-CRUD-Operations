package com.jspider.practise_simple_spring_boot_rest_api_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.practise_simple_spring_boot_rest_api_project.dto.Customer;
import com.jspider.practise_simple_spring_boot_rest_api_project.response.ApplicationResponse;
import com.jspider.practise_simple_spring_boot_rest_api_project.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value="/greet")
	public String greet() {
		return"Welcome to the Spring boot";
	}
	
	
	@GetMapping(value="/addTwoNumber/{a}/{b}")
	public int addTwoNumber(@PathVariable int a, @PathVariable int b) {
		return a+b;
	}
	
	
//	@GetMapping(value="/saveCustomer")
//	public long saveCustomerController(@RequestBody Customer customer) {
//		return customer.getPhone();
//	}
	
	
//	@GetMapping(value="/saveCustomer")
//	public Customer saveCustomerController(@RequestBody Customer customer) {
//		return customer;
//	}
	

//	@PostMapping(value="/saveCustomer")
//	public Customer saveCustmerController(@RequestBody Customer customer) {
//		return customerService.saveCustomerService(customer);
//	}
	@PostMapping(value="/saveCustomer")
	public ApplicationResponse<Customer> saveCustmerController(@RequestBody Customer customer) {
		return customerService.saveCustomerService(customer);
	}
		
	
//	@GetMapping(value="/getCustomerById/{customerId}")
//	public Customer getCustomerByIdController(@PathVariable Integer customerId) {
//		
//		return customerService.getCustomerByIdService(customerId);
//	}
	
	@GetMapping(value="/getCustomerById/{customerId}")
	public ApplicationResponse<Customer> getCustomerByIdController(@PathVariable Integer customerId) {
		
		return customerService.getCustomerByIdService(customerId);
	}
	
	
//	@GetMapping(value="/getAllCustomer")
//	public List<Customer> getAllCustomerController(){
//		return customerService.getAllCustomerService();
//	}
	
	@GetMapping(value="/getAllCustomer/{name}")
	public List<Customer> getAllSortedCustomerController(@PathVariable String name){
		return customerService.getAllSortedCustomerService(name);
	}
	
	
//	@DeleteMapping(value="/deleteCustomerById/{customerId}")
//	public String deleteCustomerController(@PathVariable int customerId) {
//		boolean bol = customerService.deleteCustomerByIdService(customerId);
//		if(bol) return "Deleted";
//		return "ID not found";
//	}
	
	
	@DeleteMapping(value="/deleteCustomerById/{customerId}")
	public ResponseEntity<String> deleteCustomerController(@PathVariable int customerId) {
		boolean bol = customerService.deleteCustomerByIdService(customerId);
		if(bol) return new ResponseEntity<>("Data Deleted", HttpStatus.OK);
		return new ResponseEntity<>("Id not Found", HttpStatus.NOT_FOUND);
	}
	
	
//	@PutMapping(value="/updateCustomer")
//	public ResponseEntity<String> updateCustomerController(@RequestBody Customer customer){
//		Customer cus = customerService.updateCustomerService(customer);
//		if(cus != null) {
//			return new ResponseEntity<>("customer updated successfully",HttpStatus.OK);
//		}
//		return new ResponseEntity<>("customer not found", HttpStatus.NOT_FOUND);
//	}
	
	
	@PutMapping(value="/updateCustomer/{customerId}")
	public ResponseEntity<String> updateCustomerController(@PathVariable int customerId, @RequestBody Customer customer){
		Customer cus = customerService.updateCustomerService(customerId, customer);
		if(cus != null) {
			return new ResponseEntity<>("customer updated successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("customer not found", HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping(value="/saveMultipleCustomer")
	public List<Customer> updateMultipleCustomer(@RequestBody List<Customer> customers){
		return customerService.saveMultipleCustomerService(customers);
	}
	
	
	@GetMapping(value="/getCustomerByEmail/{email}")
	public Customer getCustomerByEmailController(@PathVariable String email) {
		return customerService.getCustomerByEmailService(email);
	}
	
	
	@GetMapping(value="/getCustomerByPhone/{phone}")
	public Customer getCustomerByPhoneController(@PathVariable long phone) {
		return customerService.getCustomerByPhoneService(phone);
	}
	
	@GetMapping(value="/getCustomerListByName/{name}")
	public List<Customer> getCustomerListByNameController(@PathVariable String name){
		return customerService.getCustomerListByNameService(name);
	}
	
	@GetMapping(value="/getCustomerByPhoneQuery/{phone}")
	public Customer getCustomerByPhoneQueryController(@PathVariable long phone) {
		return customerService.getCustomerByPhoneQueryService(phone);
	}
	
	@DeleteMapping(value="/deleteCustomerByIdQuery/{customerId}")
	public ResponseEntity<String> deleteCustomerByIdQueryController(@PathVariable int customerId){
		boolean bol = customerService.deleteCustomerByIdQuerySevice(customerId);
		if(bol) return new ResponseEntity<>("Deleted",HttpStatus.OK);
		return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
	}
	
	
	
	
	@GetMapping(value="/filterCustomerByName/{filter}")
	public List<Customer> getAllCustomerFilterByNameController(@PathVariable String filter){
		return customerService.getAllCustomerFilterByNameService(filter);
	}
	
	
	@GetMapping(value="/getCustomerByPage/{pageNumber}/{noOfData}")
	public Page<Customer> fetchCustomerByPageController(@PathVariable int pageNumber, @PathVariable int noOfData){
		return customerService.fetchCustomerByPageService(pageNumber, noOfData);
	}
	
	
	@GetMapping(value="/getCustomerByPageAndSortByName/{pageNumber}/{noOfData}/{name}")
	public Page<Customer> fetchCustomerByPageAndSortByNameController(@PathVariable int pageNumber, @PathVariable int noOfData, @PathVariable String name){
		return customerService.fetchCustomerByPageAndSortByNameService(pageNumber, noOfData, name);
	}
	
}










