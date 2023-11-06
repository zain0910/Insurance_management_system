package com.example.demo.dto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.example.demo.model.Customer;
import com.example.demo.model.Policy;

public class ClaimDto {

	private Integer id;
	private Integer claimNumber;
	private Customer customer;
	private Set<Policy> policies = new HashSet<>();

	public ClaimDto(Integer id, Integer claimNumber, Customer customer, Set<Policy> policies) {
		super();
		this.id = id;
		this.claimNumber = claimNumber;
		this.customer = customer;
		this.policies = (policies);
	}

	public ClaimDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(Integer claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Set<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<Policy> policies) {
		this.policies = policies;

	}

}
