package com.noblemktkyc.model;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;;

/**
 * @author: java class for kyc Entity detail
 * 
 */
public class PersonalInfoModel implements Serializable, Model {
	/**
	 * 
	 */
	// personal details
	private static final long serialVersionUID = 1L;
	@Email(message = "Please enter your valid email addresss.")
	private String userName;
	@JsonIgnore
	@JsonProperty(value = "type")
	private String type;

	private String salutation;
	@NotNull
	@Pattern(regexp = "^[A-Za-z]+$")

	private String firstName;
	@NotNull

	@Pattern(regexp = "^[A-Za-z]+$")
	private String lastName;
	@NotNull
	private String dob;
	private String gender;
	@NotNull
	private String maidenName;
	private String socialSecurityNumber;
	@NotNull
	private String citizenshipCountry;
	@NotNull
	private String birthCountry;
	private String maritalStatus;
	// Contact Details
	@NotNull
	private String homeAddress;
	private String state;
	@NotEmpty
	@Pattern(regexp = "^[0-9]+$")

	private String businessPhone;
	@NotEmpty
	@Email
	private String email;
	private String streetAddress2;
	@NotEmpty
	private String countryName;
	@Pattern(regexp = "^[0-9]+$")
	private String homePhone;
	@Email
	private String alternateEmail;
	private String city;
	@NotEmpty
	private String zip;
	@Pattern(regexp = "^[0-9]+$")
	private String mobilePhone;
	// file
	private String file;
	private List<DocumentUploadDetailModel> documentUploadDetail;

	public PersonalInfoModel() {
		System.out.println("=============user model=============");

		documentUploadDetail = new ArrayList<>();
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public List<DocumentUploadDetailModel> getDocumentUploadDetail() {
		return documentUploadDetail;
	}

	public void setDocumentUploadDetail(List<DocumentUploadDetailModel> documentUploadDetail) {
		this.documentUploadDetail = documentUploadDetail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getCitizenshipCountry() {
		return citizenshipCountry;
	}

	public void setCitizenshipCountry(String citizenshipCountry) {
		this.citizenshipCountry = citizenshipCountry;
	}

	public String getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}



	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return "PersonalInfo";
	}

	public void setType(String type) {
		// this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	@Override
	public String toString() {
		return "PersonalInfoModel [userName=" + userName + ", type=" + type + ", salutation=" + salutation
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender
				+ ", maidenName=" + maidenName + ", socialSecurityNumber=" + socialSecurityNumber
				+ ", citizenshipCountry=" + citizenshipCountry + ", birthCountry=" + birthCountry + ", maritalStatus="
				+ maritalStatus + ", homeAddress=" + homeAddress + ", state=" + state + ", businessPhone="
				+ businessPhone + ", email=" + email + ", streetAddress2=" + streetAddress2 + ", country=" + countryName
				+ ", homePhone=" + homePhone + ", alternateEmail=" + alternateEmail + ", city=" + city + ", zip=" + zip
				+ ", mobilePhone=" + mobilePhone + ", file=" + file + ", documentUploadDetail=" + documentUploadDetail
				+ "]";
	}

}
