package com.jspider.practise_simple_spring_boot_rest_api_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jspider.practise_simple_spring_boot_rest_api_project.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	/**
	 * It will generate custom query to fetch customer by email.
	 * All the below methods generate the query automatically, we just have to declare method signature.
	 */
	public Customer findByEmail(String email);
	
	public Customer findByPhone(long phone);
	
	public List<Customer> findByName(String name);

	
	
	/**
	 * we can also write the own query
	 * write a own query to fetch customer by phone.
	 */
	
	@Query(value="SELECT c FROM Customer c WHERE c.phone=?1")
	public Customer getCustomerByPhone(long phone);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM Customer c WHERE c.id= :id")
	public void deleteCustomerById(@Param("id") int id);
	
	
	

	
}
