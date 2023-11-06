package com.example.demo.Converter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ClaimDto;
import com.example.demo.dto.PolicyDto;
import com.example.demo.model.Claim;
import com.example.demo.model.Policy;
import com.example.demo.repository.ClaimRepository;

@Component
public class ClaimConverter {

	public ClaimRepository cr;
	public Claim claim;
	public Optional<Claim> claim2;
	public ClaimDto claimdto;

	public Claim dtotoentity(ClaimDto claimdto) {
		Claim claim = new Claim();
		claim.setId(claimdto.getId());
		claim.setClaimNumber(claimdto.getClaimNumber());
		claim.setCustomer(claimdto.getCustomer());

		Set<Policy> policies = new HashSet<>();
		for (Policy policyDto : claimdto.getPolicies()) {
			Policy policy = new Policy();
			policy.setId(policyDto.getId());
			policy.setPolicyNumber(policyDto.getPolicyNumber());
			policy.setPolicyType(policyDto.getPolicyType());
			// Set other properties of Policy entity if needed

			policies.add(policy);
		}
		claim.setPolicies(policies);

		return claim;
	}

	public ClaimDto entitytodto(Optional<Claim> claim2) {
		ClaimDto claimdto = new ClaimDto();
		claimdto.setId(claim2.get().getId());
		claimdto.setClaimNumber(claim2.get().getClaimNumber());
		claimdto.setCustomer(claim2.get().getCustomer());

		Set<Policy> policyDtos = new HashSet<>();
		for (Policy policy : claim2.get().getPolicies()) {
			Policy policyDto = new Policy();
			policyDto.setId(policy.getId());
			policyDto.setPolicyNumber(policy.getPolicyNumber());
			policy.setPolicyType(policyDto.getPolicyType());
			// Set other properties of PolicyDto if needed

			policyDtos.add(policyDto);
		}
		claimdto.setPolicies(policyDtos);

		return claimdto;
	}

//	public ClaimDto entitytodto(Optional<Claim> claim2) {
//		ClaimDto claimdto = new ClaimDto();
//		claimdto.setId(claim2.get().getId());
//		claimdto.setClaimNumber(claim2.get().getClaimNumber());
//		claimdto.setCustomer(claim2.get().getCustomer());
//		claimdto.setPolicy(claim2.get().getPolicy());
//		
//		return claimdto;
//	}

}
