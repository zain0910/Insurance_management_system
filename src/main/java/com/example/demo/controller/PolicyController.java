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
import com.example.demo.dto.PolicyDto;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Customer;
import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;

@RestController
@RequestMapping("/Policy")
public class PolicyController {
	@Autowired
	private PolicyService ps;

	@GetMapping("/allpolicy")
	public List<Policy> Allpolicies() {
		return ps.Allpolicies();
	}

	@GetMapping("/getbyid/{id}")
	public Optional<PolicyDto> getbyid(@PathVariable Integer id) throws NotFound {
		PolicyDto policydto = ps.findbyid(id);
		return Optional.ofNullable(policydto);
	}

	@PostMapping("/add/policy")
	public ResponseEntity<String> addpolicy(@RequestBody PolicyDto policydto) {
		ps.addpolicy(policydto);
		return new ResponseEntity<String>("Policy added successfully", HttpStatus.CREATED);
	}

	@PutMapping("/update/policy/{id}")
	public ResponseEntity<String> updatepolicy(@PathVariable Integer id, @RequestBody PolicyDto policydto)
			throws NotFound {
		if (policydto.getId() != null) {
			ps.updatepolicy(id, policydto);
			return new ResponseEntity<String>("Policy updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Policy id has not been provided", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/policy/{id}")
	public ResponseEntity<String> deletepolicy(@PathVariable Integer id) throws NotFound {
		ps.deletepolicy(id);
		return new ResponseEntity<String>("Policy deleted successfully", HttpStatus.OK);
	}

}
