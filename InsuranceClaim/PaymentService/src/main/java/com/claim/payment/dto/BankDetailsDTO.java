package com.claim.payment.dto;

import com.claim.payment.constant.BankOwnerType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDetailsDTO {
	
	private Long bankDetailsId;
	private String AccountHolderName;
	private String AccountNumber;
	private String IFSCCode;
	private String BankName;
	private String linkedId;   //  hospitalId/policyholderId
	private BankOwnerType  ownerType;
}
