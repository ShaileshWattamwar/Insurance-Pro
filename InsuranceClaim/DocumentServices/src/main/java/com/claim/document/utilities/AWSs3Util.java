package com.claim.document.utilities;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;

import jakarta.annotation.PostConstruct;

@Component
public class AWSs3Util {

	private AmazonS3 amazonS3;

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.bucket}")
    private String bucketName;

	
    
    @PostConstruct
	public void initializeAmazonS3()	{
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		amazonS3 = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}

	public String uploadFile(MultipartFile file) throws IOException {

		// file stored on AWS S3
		try {
			String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());
			metadata.setContentType(file.getContentType());
			
			PutObjectResult putObject = amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
			return amazonS3.getUrl(bucketName, fileName).toString();
		} catch (AmazonServiceException e) {
			throw new IOException("Error uploading to S3: " + e.getErrorMessage(), e);
		}
	}
}
