package com.noblemktkyc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountInfoModel implements Serializable, Model {
	/**
	 * @author java class for kyc user account detail
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Email(message = "Please enter your valid email addresss.")
	private String userName;
    @JsonIgnore
	@JsonProperty(value = "type")
	private String type;
	private String accountName;
	private String code1;
	private String accountNo;
	private String bank;
	private String paymentCodeType1;
	// Bank Account Address
    @NotNull
	private String address1;
	private String state;
	private String address2;
	@NotNull
	private String zip;
	@NotNull
	private String city;
	private String accountsCountry;
	// Wallet Account Address
	private String walletAccountName;
	private String walletAddress;
	private String corraddress1;
	private String corraddress2;
	private String corrAccountsCountry;
	private String corrstate;
	private String corrcity;
	private String bankName;
	private String corrzip;

	public String getCorraddress1() {
		return corraddress1;
	}

	public void setCorraddress1(String corraddress1) {
		this.corraddress1 = corraddress1;
	}

	public String getCorraddress2() {
		return corraddress2;
	}

	public void setCorraddress2(String corraddress2) {
		this.corraddress2 = corraddress2;
	}

	

	public String getCorrstate() {
		return corrstate;
	}

	public void setCorrstate(String corrstate) {
		this.corrstate = corrstate;
	}

	public String getCorrcity() {
		return corrcity;
	}

	public void setCorrcity(String corrcity) {
		this.corrcity = corrcity;
	}

	public String getCorrzip() {
		return corrzip;
	}

	public void setCorrzip(String corrzip) {
		this.corrzip = corrzip;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getPaymentCodeType1() {
		return paymentCodeType1;
	}

	public void setPaymentCodeType1(String paymentCodeType1) {
		this.paymentCodeType1 = paymentCodeType1;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAccountsCountry() {
		return accountsCountry;
	}

	public void setAccountsCountry(String accountsCountry) {
		this.accountsCountry = accountsCountry;
	}

	public String getWalletAccountName() {
		return walletAccountName;
	}

	public void setWalletAccountName(String walletAccountName) {
		this.walletAccountName = walletAccountName;
	}

	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public String getCorrAccountsCountry() {
		return corrAccountsCountry;
	}

	public void setCorrAccountsCountry(String corrAccountsCountry) {
		this.corrAccountsCountry = corrAccountsCountry;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
