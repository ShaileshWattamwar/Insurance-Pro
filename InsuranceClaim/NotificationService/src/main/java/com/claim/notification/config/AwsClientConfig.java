package com.claim.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsClientConfig {
	
	@Value("${aws.access-key}")
    private String accessKey;

    @Value("${aws.secret-key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region; 
	
    @Bean
    SesClient sesClient() {
    	AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey, accessKey);
    	return SesClient.builder().region(Region.of(region))
    				.credentialsProvider(StaticCredentialsProvider.create(creds)).build();
    }
    
    
    @Bean
    SnsClient snsClient() {
    	AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey, accessKey);
    	return SnsClient.builder().region(Region.of(region))
    				.credentialsProvider(StaticCredentialsProvider.create(creds)).build();
    }
    
}











