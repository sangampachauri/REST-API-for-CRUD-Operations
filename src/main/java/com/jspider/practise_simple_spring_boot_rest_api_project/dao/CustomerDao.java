package com.jspider.practise_simple_spring_boot_rest_api_project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.jspider.practise_simple_spring_boot_rest_api_project.dto.Customer;
import com.jspider.practise_simple_spring_boot_rest_api_project.repository.CustomerRepository;

@Repository
public class CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
//	public Customer saveCustomer(Customer customer) {
//		return customerRepository.save(customer);
//	}
	
	
	public Customer saveCustomerDao(Customer customer) {
		try {
			return customerRepository.save(customer);
			
		}catch(IllegalArgumentException | OptimisticLockingFailureException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Customer getCustomerByIdDao(int id) {
		Optional<Customer> customer = customerRepository.findById(id);
		
//		if(customer.isPresent()) {
//			return customer.get();
//		}
//		return null;
		
		try {
			return customer.get();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Customer> getAllCustomerDao() {
		return customerRepository.findAll();
	}
	
//	public List<Customer> getAllCustomerDao() {
//		return customerRepository.findAll(Sort.by("name"));
//	}
	
	public List<Customer> getAllSortedCustomerDao(String name){
		//return customerRepository.findAll(Sort.by(name).ascending());
		//return customerRepository.findAll(Sort.by(name).descending());
		return customerRepository.findAll(Sort.by(name).reverse());
	}
	
	
	public boolean deleteCustomerByIdDao(int id) {
		Customer customer = getCustomerByIdDao(id);
		if(customer != null) {
			customerRepository.delete(customer);
			return true;
		}
		return false;
	}
	
	
//	public Customer updateCustomerDao(Customer customer) {
//		Customer customer1 = getCustomerByIdDao(customer.getId());
//		if(customer1 != null) {
//			return customerRepository.save(customer);
//		}
//		return null;
//	}
	
	public Customer updateCustomerDao(int customerId, Customer customer) {
		Customer existingCustomer = getCustomerByIdDao(customerId);
		if(existingCustomer != null) {
			if(customer.getName() != null) existingCustomer.setName(customer.getName());
			if(customer.getEmail() != null) existingCustomer.setEmail(customer.getEmail());
			if(customer.getPhone() != 0) existingCustomer.setPhone(customer.getPhone());
			
			return customerRepository.save(existingCustomer);
		}
		return null;
	}
	
	
	public List<Customer> saveMultipleCustomerDao(List<Customer> customers){
		return customerRepository.saveAll(customers);
	}
	
	
	public Customer getCustomerByEmailDao(String email) {
		return customerRepository.findByEmail(email);
	}
	
	public Customer getCustomerByPhoneDao(long phone) {
		return customerRepository.findByPhone(phone);
	}
	
	public List<Customer> getCustomerListByNameDao(String name){
		return customerRepository.findByName(name);
	}
	
	public Customer getCustomerByPhoneQueryDao(long phone) {
		return customerRepository.getCustomerByPhone(phone);
	}
	
	public boolean deleteCustomerByIdQueryDao(int id) {
		Customer existingCustomer = getCustomerByIdDao(id);
		if(existingCustomer != null) {
			customerRepository.deleteCustomerById(id);
			return true;
		}
		return false;
	}
	
	
	
	public Page<Customer> fetchCustomerByPageDao(int pageNumber, int noOfData){
		return customerRepository.findAll(PageRequest.of(pageNumber, noOfData));
	}
	
	public Page<Customer> fetchCustomerByPageAndSortByNameDao(int pageNumber, int noOfDate, String name){
		return customerRepository.findAll(PageRequest.of(pageNumber, noOfDate, Sort.by(Direction.ASC, name)));
	}
	
}







