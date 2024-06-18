package com.jspider.practise_simple_spring_boot_rest_api_project.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jspider.practise_simple_spring_boot_rest_api_project.custom_exception.CustomerAlreadyExistsException;
import com.jspider.practise_simple_spring_boot_rest_api_project.custom_exception.IdNotFoundException;
import com.jspider.practise_simple_spring_boot_rest_api_project.dao.CustomerDao;
import com.jspider.practise_simple_spring_boot_rest_api_project.dto.Customer;
import com.jspider.practise_simple_spring_boot_rest_api_project.repository.CustomerRepository;
import com.jspider.practise_simple_spring_boot_rest_api_project.response.ApplicationResponse;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ApplicationResponse<Customer> applicationResponse;
	
	@Autowired
	private CustomerRepository repo;
	
	
//	public Customer saveCustomerService(Customer customer) {
//		return customerDao.saveCustomerDao(customer);
//	}
//	public ApplicationResponse<Customer> saveCustomerService(Customer customer) {
//		Customer existingCustomer = customerDao.saveCustomerDao(customer);
//		if(existingCustomer != null) {
//			applicationResponse.setStatusCode(HttpStatus.CREATED.value());
//			applicationResponse.setStatusMsg("Customer-successfully-created");
//			applicationResponse.setDescription("I have created save customer api");
//			applicationResponse.setData(existingCustomer);
//			return applicationResponse;
//		}else {
//			applicationResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
//			applicationResponse.setStatusMsg("Failed-to-store-customer");
//			applicationResponse.setDescription("Please check your code once");
//			applicationResponse.setData(existingCustomer);
//			return applicationResponse;
//		}
//	}
	public ApplicationResponse<Customer> saveCustomerService(Customer customer) {
		Optional<Customer> existingCustomer  = repo.findById(customer.getId());
		if(!existingCustomer.isPresent()) {
			customerDao.saveCustomerDao(customer);
			applicationResponse.setStatusCode(HttpStatus.CREATED.value());
			applicationResponse.setStatusMsg("Customer-successfully-created");
			applicationResponse.setDescription("I have created save customer api");
			applicationResponse.setData(customer);
			return applicationResponse;
		}else {
			throw new CustomerAlreadyExistsException("Customer already exits!!!!");
		}
	}
	
	
//	public Customer getCustomerByIdService(int id) {
//		return customerDao.getCustomerByIdDao(id);
//	}
	
	public ApplicationResponse<Customer> getCustomerByIdService(int id) {
		Customer existingCustomer = customerDao.getCustomerByIdDao(id);
		if(existingCustomer != null) {
			applicationResponse.setStatusCode(HttpStatus.FOUND.value());
			applicationResponse.setStatusMsg("Id of given customer found");
			applicationResponse.setDescription("Detail of found customer");
			applicationResponse.setData(existingCustomer);
			return applicationResponse;
		}else {
			throw new IdNotFoundException("Id not found");
		}
	}
	
	
	
	
	
//	public List<Customer> getAllCustomerService() {
//		return customerDao.getAllCustomerDao();
//	}
	
//	public List<Customer> getAllCustomerService() {
//		return customerDao.getAllCustomerDao();
//	}
	
	public List<Customer> getAllSortedCustomerService(String name){
		return customerDao.getAllSortedCustomerDao(name);	
	}
	
	
	public boolean deleteCustomerByIdService(int id) {
		return customerDao.deleteCustomerByIdDao(id);
	}
	
	
//	public Customer updateCustomerService(Customer customer) {
//		return customerDao.updateCustomerDao(customer);
//	}
	
	
	public Customer updateCustomerService(int customerId, Customer customer) {
		return customerDao.updateCustomerDao(customerId, customer);
	}
	
	public List<Customer> saveMultipleCustomerService(List<Customer> customers){
		return customerDao.saveMultipleCustomerDao(customers);
	}
	
	
	public Customer getCustomerByEmailService(String email) {
		return customerDao.getCustomerByEmailDao(email);
	}

	
	public Customer getCustomerByPhoneService(long phone) {
		return customerDao.getCustomerByPhoneDao(phone);
	}
	
	public List<Customer> getCustomerListByNameService(String name){
		return customerDao.getCustomerListByNameDao(name);
	}
	
	public Customer getCustomerByPhoneQueryService(long phone) {
		return customerDao.getCustomerByPhoneQueryDao(phone);
	}
	
	
	public boolean deleteCustomerByIdQuerySevice(int id) {
		return customerDao.deleteCustomerByIdQueryDao(id);
	}
	
	
	
	
//	public List<Customer> getAllCustomerFilterByNameService(String filter){
//		return customerDao.getAllCustomerDao()
//				.stream()
//				.filter(a->a.getName()
//						.equals(filter)).toList();
//	}
	
//	public List<Customer> getAllCustomerFilterByNameService(String filter){
//		return customerDao.getAllCustomerDao()
//				.stream()
//				.filter(a->a.getName().equals(filter))
//				.sorted((a,b) -> a.getEmail().compareTo(b.getEmail()))
//				.collect(Collectors.toList());
//	}
	
//	public List<Customer> getAllCustomerFilterByNameService(String filter){
//		return customerDao.getAllCustomerDao()
//				.stream()
//				.filter(a->a.getName().equals(filter))
//				.sorted((a,b) -> a.getEmail().compareTo(b.getEmail())).toList();
//	}
	
//	public List<Customer> getAllCustomerFilterByNameService(String filter){
//		return customerDao.getAllCustomerDao()
//				.stream()
//				.filter(a->a.getName().equals(filter))
//				.sorted(Comparator.comparingInt(a -> a.getId()))
//				.collect(Collectors.toList());
//	}
	
	public List<Customer> getAllCustomerFilterByNameService(String filter){
		return customerDao.getAllCustomerDao()
				.stream()
				.filter(a->a.getName().equals(filter))
				.sorted(Comparator.comparingInt(Customer::getId))
				.collect(Collectors.toList());
	}
	
	
	
	public Page<Customer> fetchCustomerByPageService(int pageNumber, int noOfData){
		return customerDao.fetchCustomerByPageDao(pageNumber, noOfData);
	}
	
	public Page<Customer> fetchCustomerByPageAndSortByNameService(int pageNumber, int noOfDate, String name){
		return customerDao.fetchCustomerByPageAndSortByNameDao(pageNumber, noOfDate, name);
	}
	
}
