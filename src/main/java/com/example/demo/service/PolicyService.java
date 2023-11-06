package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Converter.PolicyConverter;
import com.example.demo.dto.PolicyDto;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;

@Service
public class PolicyService {
	@Autowired
	public PolicyRepository pcr;
	public Optional<Policy> policy;
	@Autowired
	public PolicyConverter pc;
	public PolicyDto policydto;

	public String addpolicy(PolicyDto policydto) {
		pcr.save(pc.dtotoentity(policydto));
		return "Policy added successfully";
	}

	public String updatepolicy(Integer id, PolicyDto policydto) throws NotFound {
		try {
			pcr.save(pc.dtotoentity(policydto));
			return "Policy updated successfully";
		} catch (Exception e) {
			throw new NotFound("Policy with policy id-" + id + " not found");
		}
	}

	public PolicyDto findbyid(Integer id) throws NotFound {
		try {
			policy = pcr.findById(id);
			policydto = pc.entitytodto(policy);
			return policydto;
		} catch (Exception e) {
			throw new NotFound("Policy with policy id-" + id + " not found");
		}
	}

	public List<Policy> Allpolicies() {
		List<Policy> policies = new ArrayList<>();
		pcr.findAll().forEach(policies::add);
		return policies;
	}

	public String deletepolicy(Integer id) throws NotFound {
		try {
			pcr.deleteById(id);
			return "Policy deleted successfully";
		} catch (Exception e) {
			throw new NotFound("Policy with policy id-" + id + " not found");
		}
	}

}
