package com.NewSupplierModule.DTO;

public class SupplierDetailsDTO {
    private String name;
    private String registrationNumber;
    private String taxId;
    private String contactEmail;
    private String contactNumber;
    private String bankAccount;
    private String ifscCode;
    private boolean documentsUploaded;

    public SupplierDetailsDTO() {

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

    public SupplierDetailsDTO(String name, String registrationNumber, String taxId, String contactEmail, String contactNumber, String bankAccount, String ifscCode, boolean documentsUploaded) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.taxId = taxId;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.bankAccount = bankAccount;
        this.ifscCode = ifscCode;
        this.documentsUploaded = documentsUploaded;
    }

    public void setApproved(boolean b) {
    }

    public void setId(long l) {
    }

    public void setEmail(String mail) {
    }
}
