package com.example.demo.dto;

public class PolicyDto {

	public Integer id;
	public String policyNumber;
	public String policyType;

	public PolicyDto(Integer id, String policyNumber, String policyType) {
		super();
		this.id = id;
		this.policyNumber = policyNumber;
		this.policyType = policyType;
	}

	public PolicyDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

}
