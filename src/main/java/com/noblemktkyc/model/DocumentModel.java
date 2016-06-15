package com.noblemktkyc.model;

public class DocumentModel {
	private String newFileName;
	private String orignalName;
	private String documentType;
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getNewFileName() {
		return newFileName;
	}
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	public String getOrignalName() {
		return orignalName;
	}
	public void setOrignalName(String orignalName) {
		this.orignalName = orignalName;
	}

}
