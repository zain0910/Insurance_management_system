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

import com.example.demo.dto.ClaimDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.AlreadyClaimed;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Claim;
import com.example.demo.model.Customer;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.service.ClaimService;

@RestController
@RequestMapping("/Claim")
public class ClaimController {
	@Autowired
	public ClaimRepository cr;
	@Autowired
	public ClaimService cs;

	@GetMapping("/allclaims")
	public List<Claim> AllClaims() {
		return cs.AllClaims();
	}

	@GetMapping("/claim/getbyid/{id}")
	public Optional<ClaimDto> getbyid(@PathVariable Integer id) throws NotFound {
		ClaimDto claimdto = cs.findbyid(id);
		return Optional.ofNullable(claimdto);
	}

	@PostMapping("/add/claim/{customerid}/{policyid}")
	public ResponseEntity<String> addclaim(@PathVariable("customerid") Integer customerid,
			@PathVariable("policyid") Integer policyid, @RequestBody ClaimDto claimdto)
			throws AlreadyClaimed, NotFound {
		cs.addclaim(customerid, policyid, claimdto);
		return new ResponseEntity<String>("Claim added successfully", HttpStatus.CREATED);
	}

	@PostMapping("/update/claim/{customerid}/{policyid}")
	public ResponseEntity<String> updateclaim(@PathVariable("customerid") Integer customerid,
			@PathVariable("policyid") Integer policyid, @RequestBody ClaimDto claimdto)
			throws AlreadyClaimed, NotFound {
		cs.addclaim(customerid, policyid, claimdto);
		return new ResponseEntity<String>("Claim updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/delete/claim/{id}")
	public ResponseEntity<String> deleteclaim(@PathVariable Integer id) throws NotFound {
		cs.deleteclaim(id);
		return new ResponseEntity<String>("Claim deleted successfully", HttpStatus.OK);
	}

}
