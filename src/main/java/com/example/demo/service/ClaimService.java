package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.demo.Converter.ClaimConverter;
import com.example.demo.dto.ClaimDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.AlreadyClaimed;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Claim;
import com.example.demo.model.Customer;
import com.example.demo.model.Policy;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PolicyRepository;

@Service
public class ClaimService {
	@Autowired
	public ClaimRepository cr;
	public Optional<Claim> claim;
	public ClaimDto claimdto;
	@Autowired
	public ClaimConverter cc;
	@Autowired
	public PolicyRepository pr;
	@Autowired
	public CustomerRepository cmr;

	public String addclaim(Integer customerid, Integer policyid, ClaimDto claimdto) throws AlreadyClaimed, NotFound {
		Optional<Customer> customer = Optional.ofNullable(new Customer());
		customer = cmr.findById(customerid);
		Optional<Policy> policy = Optional.of(new Policy());
		policy = pr.findById(policyid);
		if (customer.isPresent() && policy.isPresent()) {
			claimdto.setCustomer(customer.get());
			Set<Policy> policies = new HashSet<>();
			policies.add(policy.get());
			claimdto.setPolicies(policies);
			try {
				cr.save(cc.dtotoentity(claimdto));
			} catch (Exception e) {
				throw new AlreadyClaimed("This Customer with customer id-" + customerid
						+ " has already claimed against policy with policy id-" + policyid);
			}
			return "Claim added successfully";
		} else {
			throw new NotFound("Either customer or policy or both not found");
		}
	}

	public List<Claim> AllClaims() {
		List<Claim> claims = new ArrayList<>();
		cr.findAll().forEach(claims::add);
		return claims;
	}

	public ClaimDto findbyid(Integer id) throws NotFound {
		try {
			claim = cr.findById(id);
			claimdto = cc.entitytodto(claim);
			return claimdto;
		} catch (Exception e) {
			throw new NotFound("Claim with claim id-" + id + " not found");
		}

	}

	public String updateclaim(Integer id, ClaimDto claimdto) throws NotFound {
		try {
			cr.save(cc.dtotoentity(claimdto));
			return "Claim updated successfully";
		} catch (Exception e) {
			throw new NotFound("Claim with claim id-" + id + " not found");
		}
	}

	public String deleteclaim(Integer id) throws NotFound {
		try {
			cr.deleteById(id);
			return "Claim deleted successfully";
		} catch (Exception e) {
			throw new NotFound("Claim with claim id-" + id + " not found");
		}
	}

}