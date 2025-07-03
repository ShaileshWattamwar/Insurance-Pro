package com.insurance.claim.services.interfaces;

import com.insurance.claim.dto.request.CashlessRequestDTO;
import com.insurance.claim.dto.request.ReimbursementRequestDTO;
import com.insurance.claim.dto.response.CashlessResponseDTO;
import com.insurance.claim.dto.response.ReimbursementResponseDTO;

public interface HealthClaimService {
	
	CashlessResponseDTO initiateCashlessClaim(CashlessRequestDTO RequestDTO);
	ReimbursementResponseDTO initiateReimbursementClaim(ReimbursementRequestDTO requestDTO);
}
