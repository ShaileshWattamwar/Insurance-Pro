package com.NewSupplierModule.Entity;

import com.NewSupplierModule.Model.SupplierStatus;
//import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
//import jakarta.persistence.EnumType;

@Document
public class SupplierDetails {
    @Id
    private String id;

    private String name;
    private String registrationNumber;
    private String taxId;

    private String contactEmail;
    private String contactNumber;

    private String bankAccount;
    private String ifscCode;

    private boolean documentsUploaded;


    private SupplierStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SupplierDetails() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public boolean isDocumentsUploaded() {
        return documentsUploaded;
    }

    public void setDocumentsUploaded(boolean documentsUploaded) {
        this.documentsUploaded = documentsUploaded;
    }

    public SupplierStatus getStatus() {
        return status;
    }

    public void setStatus(SupplierStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SupplierDetails(String id, String name, String registrationNumber, String taxId, String contactEmail, String contactNumber, String bankAccount, String ifscCode, boolean documentsUploaded, SupplierStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.taxId = taxId;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.bankAccount = bankAccount;
        this.ifscCode = ifscCode;
        this.documentsUploaded = documentsUploaded;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
