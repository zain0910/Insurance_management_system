package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

	@Autowired
	private CustomerService cs;
	@Autowired
	private CustomerRepository cr;

	@GetMapping("/allcustomer")
	public List<Customer> AllCustomers() {
		return cs.AllCustomers();
	}
	
	@GetMapping("/customer/getbyid/{id}")
	public Optional<CustomerDto> getbyid(@PathVariable Integer id) throws NotFound {
	    CustomerDto customerdto = cs.findbyid(id);
		return Optional.ofNullable(customerdto);
	}

	@PostMapping("/add/customer")
	public ResponseEntity<String> addcustomer(@RequestBody CustomerDto customerdto) {
		cs.addcustomer(customerdto);
		return new ResponseEntity<String>("Customer added successfully", HttpStatus.CREATED);
	}

	@PutMapping("/update/cutomer/{id}")
	public ResponseEntity<String> updatecustomer(@PathVariable Integer id, @RequestBody CustomerDto customerdto) throws NotFound {
		if(customerdto.getId() != null) {
		cs.updatecustomer(id, customerdto);
		return new ResponseEntity<String>("Customer updated successfully", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Customer id has not been provided", HttpStatus.BAD_REQUEST); 
		}			
	}

	@DeleteMapping("/delete/customer/{id}")
	public ResponseEntity<String> deletecustomer(@PathVariable Integer id) throws NotFound {
		cs.deletecustomer(id);
		return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
	}
}
