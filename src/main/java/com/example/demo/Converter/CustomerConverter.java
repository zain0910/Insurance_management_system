package com.example.demo.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Component
public class CustomerConverter {
	@Autowired
	public CustomerRepository cmr;
	public Optional<Customer> customer2;
	public Customer customer;
	public CustomerDto customerdto;

	public Customer dtotoentity(CustomerDto customerdto) {
		Customer customer = new Customer();
		customer.setId(customerdto.getId());
		customer.setFirstName(customerdto.getFirstName());
		customer.setLastName(customerdto.getLastName());
		customer.setPhoneNumber(customerdto.getPhoneNumber());
		customer.setEmail(customerdto.getEmail());
		customer.setPassword(customerdto.getPassword());

		return customer;
	}

	public CustomerDto entitytodto(Optional<Customer> customer2) {
		CustomerDto customerdto = new CustomerDto();
		customerdto.setId(customer2.get().getId());
		customerdto.setFirstName(customer2.get().getFirstName());
		customerdto.setLastName(customer2.get().getLastName());
		customerdto.setPhoneNumber(customer2.get().getPhoneNumber());
		customerdto.setEmail(customer2.get().getEmail());
		customerdto.setPassword(customer2.get().getPassword());

		return customerdto;
	}

}
