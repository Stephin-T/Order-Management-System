package com.training.task.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerDetailsDTO {
	
	
    private Long customerId;
    @NotBlank(message= "First Name should not be empty.")
    @Size(min = 1, message = "First name should not be empty.")
    private String firstName;
    private String middleName;
    
    @NotBlank(message= "First Name should not be empty.")
    @Size(min = 1, message = "Last name should not be empty.")
    private String lastName;
    
    @Email(message="Enetr a valid Email ID")
    private String email;
    
    @NotBlank(message= "Company name should not be empty.")
    @Size(min = 1, message = "Company name should not be empty.")
    private String company;
    
    @NotBlank(message= "Street should not be empty.")
    @Size(min = 1, message = "Street name should not be empty.")
    private String billingStreet;
    
    @NotBlank(message= "City should not be empty.")
    @Size(min = 1, message = "City name should not be empty.")
    private String billingCity;
    
    @NotBlank(message= "State should not be empty.")
    @Size(min = 1, message = "State name should not be empty.")
    private String billingState;
    
    @NotBlank(message= "Zip code should not be empty.")
    @Size(min = 1, message = "Zip code should not be empty.")
    private String billingZip;

   
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }
}
