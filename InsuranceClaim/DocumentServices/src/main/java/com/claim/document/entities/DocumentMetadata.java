package com.claim.document.entities;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "documents")	
public class DocumentMetadata {
	
	@Id
	private String id;            // UUID + filename
    private String fileName;
    private long size;
    private String s3Url;
    private String referenceId;   // claimId or preAuthId
    private String claimType;     // PRE_AUTH or CLAIM
    private String admissionType;
    private String fileType;
    private String uploadedBy;
    private LocalDateTime uploadedAt;
}
