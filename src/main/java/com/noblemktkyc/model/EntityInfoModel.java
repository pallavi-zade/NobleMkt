package com.noblemktkyc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author java class for kyc Entity detail
 * 
 */
public class EntityInfoModel implements Serializable, Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	@JsonIgnore
	@JsonProperty(value = "type")
	private String type;
	private String accountPurpose;
	private String companyName;
	@NotNull
	private String employerId;
	@NotNull
	private String baseCurrency;
	@NotNull
	private String sourceOfFunds;
	private String typeOfBusiness;
	@NotNull
	private String hqAddress;
	@NotNull
	private String hqStreetAddress2;
	@NotNull
	private String hqCountryName;
	private String hqstate;
	@NotNull
	private String hqCity;
	@NotNull
	private String hqZip;
	private String mailingAddress;
	private String mailingStreetAddress2;
	private String mailingCountryName;
	private String mailingState;
	private String mailingCity;
	private String mailingZip;
	private String fax;
	@NotNull
	@Pattern(regexp = "^[0-9]+$")
	private String phone;
	@NotNull
	@Pattern(regexp = "^[0-9]+$")
	private String alternatePhone;
	@Pattern(regexp = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")
	private String website;
	@Email
	@NotNull
	private String email;
	@NotNull
	private String jurisdiction;
	private String reportsEmail;
	@NotNull
	private String trademarkName;
	@NotNull
	@Pattern(regexp = "^[0-9]+$")
	private String transferCallbackPhone;
	@NotNull
	@Email
	private String transferCallbackEmail;
	@NotNull
	private String registeredAddress1;
	private String registeredAddress2;
	@NotNull
	private String registeredCity;

	private String registeredState;
	@NotNull
	private String registeredCountry;
	@NotNull
	private String registeredPincode;
	private List<DocumentModel> entityDocUpload;

	private List<DocumentModel> enhanceDueDiligence;

	public EntityInfoModel() {
		super();
		entityDocUpload = new ArrayList<DocumentModel>();
		enhanceDueDiligence = new ArrayList<DocumentModel>();
	}

	public String getUserName() {
		return userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountPurpose() {
		return accountPurpose;
	}

	public void setAccountPurpose(String accountPurpose) {
		this.accountPurpose = accountPurpose;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmployerId() {
		return employerId;
	}

	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getSourceOfFunds() {
		return sourceOfFunds;
	}

	public void setSourceOfFunds(String sourceOfFunds) {
		this.sourceOfFunds = sourceOfFunds;
	}

	public String getTypeOfBusiness() {
		return typeOfBusiness;
	}

	public void setTypeOfBusiness(String typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
	}

	public String getHqAddress() {
		return hqAddress;
	}

	public void setHqAddress(String hqAddress) {
		this.hqAddress = hqAddress;
	}

	public String getHqStreetAddress2() {
		return hqStreetAddress2;
	}

	public void setHqStreetAddress2(String hqStreetAddress2) {
		this.hqStreetAddress2 = hqStreetAddress2;
	}


	public String getHqstate() {
		return hqstate;
	}

	public void setHqstate(String hqstate) {
		this.hqstate = hqstate;
	}

	public String getHqCity() {
		return hqCity;
	}

	public void setHqCity(String hqCity) {
		this.hqCity = hqCity;
	}

	public String getHqZip() {
		return hqZip;
	}

	public void setHqZip(String hqZip) {
		this.hqZip = hqZip;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getMailingStreetAddress2() {
		return mailingStreetAddress2;
	}

	public void setMailingStreetAddress2(String mailingStreetAddress2) {
		this.mailingStreetAddress2 = mailingStreetAddress2;
	}



	public String getHqCountryName() {
		return hqCountryName;
	}

	public void setHqCountryName(String hqCountryName) {
		this.hqCountryName = hqCountryName;
	}

	public String getMailingCountryName() {
		return mailingCountryName;
	}

	public void setMailingCountryName(String mailingCountryName) {
		this.mailingCountryName = mailingCountryName;
	}

	public String getMailingState() {
		return mailingState;
	}

	public void setMailingState(String mailingState) {
		this.mailingState = mailingState;
	}

	public String getMailingCity() {
		return mailingCity;
	}

	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}

	public String getMailingZip() {
		return mailingZip;
	}

	public void setMailingZip(String mailingZip) {
		this.mailingZip = mailingZip;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getReportsEmail() {
		return reportsEmail;
	}

	public void setReportsEmail(String reportsEmail) {
		this.reportsEmail = reportsEmail;
	}

	public String getTrademarkName() {
		return trademarkName;
	}

	public void setTrademarkName(String trademarkName) {
		this.trademarkName = trademarkName;
	}

	public String getTransferCallbackPhone() {
		return transferCallbackPhone;
	}

	public void setTransferCallbackPhone(String transferCallbackPhone) {
		this.transferCallbackPhone = transferCallbackPhone;
	}

	public String getTransferCallbackEmail() {
		return transferCallbackEmail;
	}

	public void setTransferCallbackEmail(String transferCallbackEmail) {
		this.transferCallbackEmail = transferCallbackEmail;
	}

	public String getRegisteredAddress1() {
		return registeredAddress1;
	}

	public void setRegisteredAddress1(String registeredAddress1) {
		this.registeredAddress1 = registeredAddress1;
	}

	public String getRegisteredAddress2() {
		return registeredAddress2;
	}

	public void setRegisteredAddress2(String registeredAddress2) {
		this.registeredAddress2 = registeredAddress2;
	}

	public String getRegisteredCity() {
		return registeredCity;
	}

	public void setRegisteredCity(String registeredCity) {
		this.registeredCity = registeredCity;
	}

	public String getRegisteredState() {
		return registeredState;
	}

	public void setRegisteredState(String registeredState) {
		this.registeredState = registeredState;
	}

	public String getRegisteredCountry() {
		return registeredCountry;
	}

	public void setRegisteredCountry(String registeredCountry) {
		this.registeredCountry = registeredCountry;
	}

	public String getRegisteredPincode() {
		return registeredPincode;
	}

	public void setRegisteredPincode(String registeredPincode) {
		this.registeredPincode = registeredPincode;
	}

	public List<DocumentModel> getEntityDocUpload() {
		return entityDocUpload;
	}

	public void setEntityDocUpload(List<DocumentModel> entityDocUpload) {
		this.entityDocUpload = entityDocUpload;
	}

	public List<DocumentModel> getEnhanceDueDiligence() {
		return enhanceDueDiligence;
	}

	public void setEnhanceDueDiligence(List<DocumentModel> enhanceDueDiligence) {
		this.enhanceDueDiligence = enhanceDueDiligence;
	}

	@Override
	public String toString() {
		return "EntityInfoModel [userName=" + getUserName() + ", accountPurpose=" + accountPurpose + ", companyName="
				+ companyName + ", employerId=" + employerId + ", baseCurrency=" + baseCurrency + ", sourceOfFunds="
				+ sourceOfFunds + ", typeOfBusiness=" + typeOfBusiness + ", hqAddress=" + hqAddress
				+ ", hqStreetAddress2=" + hqStreetAddress2 + ", hqCountry=" + hqCountryName + ", hqstate=" + hqstate
				+ ", hqCity=" + hqCity + ", hqZip=" + hqZip + ", mailingAddress=" + mailingAddress
				+ ", mailingStreetAddress2=" + mailingStreetAddress2 + ", mailingCountry=" + mailingCountryName
				+ ", mailingState=" + mailingState + ", mailingCity=" + mailingCity + ", mailingZip=" + mailingZip
				+ ", fax=" + fax + ", phone=" + phone + ", alternatePhone=" + alternatePhone + ", website=" + website
				+ ", email=" + email + ", jurisdiction=" + jurisdiction + ", reportsEmail=" + reportsEmail
				+ ", trademarkName=" + trademarkName + ", transferCallbackPhone=" + transferCallbackPhone
				+ ", transferCallbackEmail=" + transferCallbackEmail + ", registeredAddress1=" + registeredAddress1
				+ ", registeredAddress2=" + registeredAddress2 + ", registeredCity=" + registeredCity
				+ ", registeredState=" + registeredState + ", registeredCountry=" + registeredCountry
				+ ", registeredPincode=" + registeredPincode + ", entityDocUpload=" + entityDocUpload + ", "
				// + "file=" + file
				+ ", enhanceDueDiligence=" + enhanceDueDiligence
				// + ", eddFile=" + eddFile
				+ "]";
	}

}
