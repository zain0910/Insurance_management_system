package com.example.demo.Converter;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.dto.PolicyDto;
import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;

@Component
public class PolicyConverter {
	@Autowired
	public PolicyRepository pcr;
	public Optional<Policy> policy2;
	public Policy policy;
	public PolicyDto policydto;

	public Policy dtotoentity(PolicyDto policydto) {
		Policy policy = new Policy();
		policy.setId(policydto.getId());
		policy.setPolicyNumber(policydto.getPolicyNumber());
		policy.setPolicyType(policydto.getPolicyType());

		return policy;
	}

	public PolicyDto entitytodto(Optional<Policy> policy2) {
		PolicyDto policydto = new PolicyDto();
		policydto.setId(policy2.get().getId());
		policydto.setPolicyNumber(policy2.get().getPolicyNumber());
		policydto.setPolicyType(policy2.get().getPolicyType());

		return policydto;
	}

}
