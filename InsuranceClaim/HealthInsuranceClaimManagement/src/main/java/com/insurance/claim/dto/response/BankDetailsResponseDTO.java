package com.insurance.claim.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDetailsResponseDTO {
	
	private String AccountHolderName;
	private String BankName;
	private String AccountNumber;
	private String IFSCCode;	
}
