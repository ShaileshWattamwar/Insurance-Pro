package com.insurance.claim.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.insurance.claim.dto.HospitalDTO;
import com.insurance.claim.dto.request.CashlessRequestDTO;
import com.insurance.claim.dto.request.ReimbursementRequestDTO;
import com.insurance.claim.entities.HospitalEntity;
import com.insurance.claim.entities.PolicyClaimEntity;
import com.insurance.claim.entities.PolicyHolder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MappingUtils {

	private final ModelMapper modelMapper;
	
	public PolicyClaimEntity cashlessToEntity(CashlessRequestDTO reqDTO, PolicyHolder holder, HospitalEntity hospital) {
		
		return PolicyClaimEntity.builder()
				.admissionType(reqDTO.getAdmissionType())
				.claimType(reqDTO.getClaimType())
				.patientName(reqDTO.getPatientName())
				.disease(reqDTO.getDisease())
				.doctorName(reqDTO.getDoctorName())
				.expectedCost(reqDTO.getExpectedCost())
				.treatmentType(reqDTO.getTreatmentType())
				.submittedBy(reqDTO.getSubmittedBy())
				.preAuthId(reqDTO.getPreAuthId())
				.hospital(hospital)				// fetch from Hospital table
				.policyHolder(holder)
				.bankDetailsId(null) 			// fetch from PaymentService
				.build();
	}
	
	
	public PolicyClaimEntity reimbursementToEntity(ReimbursementRequestDTO reqDto, Long paymentDetailsId, PolicyHolder policyHolder) {
	
		HospitalDTO hospitalDTO = reqDto.getHospitalDetails();
		
//		HospitalEntity hospitalEntity = HospitalEntity.builder()
//					.hospitalName(hospitalDTO.getHospitalName())
//					.hospitalType(hospitalDTO.getHospitalType())
//					.contact(hospitalDTO.getContact())
//					.street(hospitalDTO.getStreet())
//					.city(hospitalDTO.getCity())
//					.state(hospitalDTO.getState())
//					.pincode(hospitalDTO.getPincode())
//					.build();
		
		HospitalEntity hospitalEntity = modelMapper.map(hospitalDTO, HospitalEntity.class);
		
		
		return PolicyClaimEntity.builder()
				.admissionType(reqDto.getAdmissionType())
				.claimType(reqDto.getClaimType())
				.patientName(reqDto.getPatientName())
				.disease(reqDto.getDisease())
				.doctorName(reqDto.getDoctorName())
				.treatmentType(reqDto.getTreatmentType())
				.claimedAmount(reqDto.getClaimedAmount())
				.approvedAmount(null)
				.submittedBy(reqDto.getSubmittedBy())
				.hospital(hospitalEntity)
				.policyHolder(policyHolder)
				.paymentDetailsId(paymentDetailsId)
				.bankDetailsId(reqDto.getBankDetailsId())
				.build();
		
	}
}
