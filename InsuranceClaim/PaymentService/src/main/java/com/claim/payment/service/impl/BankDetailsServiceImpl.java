package com.claim.payment.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.claim.payment.dto.BankDetailsDTO;
import com.claim.payment.entities.BankDetails;
import com.claim.payment.repos.BankDetailsRepository;
import com.claim.payment.service.BankDetailsService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BankDetailsServiceImpl implements BankDetailsService {

	private final BankDetailsRepository repository;
	private final ModelMapper modelMapper;

	@Override
	public BankDetailsDTO createBankDetails(BankDetailsDTO dto) {
		BankDetails entity = modelMapper.map(dto, BankDetails.class);
		return modelMapper.map(repository.save(entity), BankDetailsDTO.class);

	}

	@Override
	public BankDetailsDTO getBankDetailsById(Long id) {
		BankDetails entity = repository.findById(id).orElseThrow(() -> new RuntimeException("BankDetails not found"));
		return modelMapper.map(entity, BankDetailsDTO.class);
	}

}
