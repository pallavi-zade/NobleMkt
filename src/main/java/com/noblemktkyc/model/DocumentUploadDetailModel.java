package com.noblemktkyc.model;



import org.hibernate.validator.constraints.NotEmpty;

//java class for upload document detail
public class DocumentUploadDetailModel extends DocumentModel {

	private String dtype;
	@NotEmpty
	private String idIssueDate;
	private String idNo;
	@NotEmpty
	private String  idExpiryDate;

	public DocumentUploadDetailModel() {
		System.out.println("============inside document==>DocumentUploadDetail");
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String type) {
		this.dtype = type;
	}

	public String getIdIssueDate() {
		return idIssueDate;
	}

	public void setIdIssueDate(String idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdExpiryDate() {
		return idExpiryDate;
	}

	public void setIdExpiryDate( String idExpiryDate) {
		this.idExpiryDate = idExpiryDate;
	}

	@Override
	public String toString() {
		return "DocumentUploadDetailModel [newFileName=" + getNewFileName() + ", documentType=" + getDocumentType() + ", idType="
				+ dtype + ", idIssueDate=" + idIssueDate + ", idNo=" + idNo + ", idExpiryDate=" + idExpiryDate + "]";
	}

}
