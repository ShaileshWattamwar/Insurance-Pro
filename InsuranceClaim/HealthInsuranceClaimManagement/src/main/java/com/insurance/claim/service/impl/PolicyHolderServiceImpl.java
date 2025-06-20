package com.insurance.claim.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.insurance.claim.dto.PolicyHolderDTO;
import com.insurance.claim.entities.PolicyHolder;
import com.insurance.claim.exceptions.ResourceNotFoundException;
import com.insurance.claim.repositories.PolicyHolderRepository;
import com.insurance.claim.services.interfaces.PolicyHolderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolicyHolderServiceImpl implements PolicyHolderService {

	private final PolicyHolderRepository holderRepo;
	private final ModelMapper modelMapper;

	@Override
	public PolicyHolderDTO getPolicyHolderById(String id) {
		PolicyHolder policyHolder = holderRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("PolicyHolder Not Found Exception with ID::" + id));
		return modelMapper.map(policyHolder, PolicyHolderDTO.class);
	}

	@Override
	public List<PolicyHolderDTO> getAllPolicyHolders() {
		List<PolicyHolder> allPolicyHolders = holderRepo.findAll();
		return allPolicyHolders.stream().map(policyHolder -> modelMapper.map(policyHolder, PolicyHolderDTO.class))
				.collect(Collectors.toList());
	}

}
