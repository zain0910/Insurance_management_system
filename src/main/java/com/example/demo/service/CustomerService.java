package com.example.demo.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Converter.CustomerConverter;
import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	public CustomerRepository cmr;
	public Optional<Customer> customer;
	@Autowired
	public CustomerConverter cc;
	public CustomerDto customerdto;

	public String addcustomer(CustomerDto customerdto) {
		cmr.save(cc.dtotoentity(customerdto));
		return "Customer added successfully";
	}

	public String updatecustomer(Integer id, CustomerDto customerdto) throws NotFound {
		try {
			cmr.save(cc.dtotoentity(customerdto));
			return "Customer updated successfully";
		} catch (Exception e) {
			throw new NotFound("Customer with customer id-" + id + " not found");
		}
	}

	public String deletecustomer(Integer id) throws NotFound {
		try {
			cmr.deleteById(id);
			return "Customer deleted successfully";
		} catch (Exception e) {
			throw new NotFound("Customer with customer id-" + id + " not found");
		}
	}

	public CustomerDto findbyid(Integer id) throws NotFound {
		try {
			customer = cmr.findById(id);
			customerdto = cc.entitytodto(customer);
			return customerdto;
		} catch (Exception e) {
			throw new NotFound("Customer with customer id-" + id + " not found");
		}
	}

	public List<Customer> AllCustomers() {
		List<Customer> customers = new ArrayList<>();
		cmr.findAll().forEach(customers::add);
		return customers;
	}
}
