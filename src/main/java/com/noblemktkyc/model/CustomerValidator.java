package com.noblemktkyc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Model.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");
		if (target != null) {
			if (target instanceof PersonalInfoModel) {
				 int index = 0; 

				PersonalInfoModel user = (PersonalInfoModel) target;
				// validation on PersonalInfo
				// alternet email and email id must be diff
				// dob should not be a future date
				if (user != null && user.getDob() != null) {
					Date date = new Date();
					try {
						
					if(dateFormat.parse(dateFormat.format(new Date())).compareTo(dateFormat.parse(user.getDob()))<0)
					
							errors.rejectValue("dob", "dob.validation");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (user != null && user.getAlternateEmail() != null && user.getEmail() != null) {
					if (user.getAlternateEmail().equals(user.getEmail())) {
						errors.rejectValue("email", "email.match");
					}
				}
				// issue date must be erlier than expiry date
				//issue date should not be future date
				for (DocumentUploadDetailModel documentUploadDetailModel : user.getDocumentUploadDetail()) {

					try {
						Date date = new Date();
						
						if (documentUploadDetailModel != null) {
							if (documentUploadDetailModel.getIdExpiryDate() != null
									&& documentUploadDetailModel.getIdIssueDate() != null) {
							
								if(dateFormat.parse(dateFormat.format(new Date())).compareTo(dateFormat.parse(documentUploadDetailModel.getIdIssueDate()))<0)

								{ 
									System.out.println(date+"_"+documentUploadDetailModel.getIdIssueDate()+"issue date should not be future date");
									errors.rejectValue("documentUploadDetail[" + index + "].idIssueDate", "idIssueDate."+documentUploadDetailModel.getDtype());

								}
								
								if (dateFormat.parse(documentUploadDetailModel.getIdExpiryDate())
										.compareTo(dateFormat.parse(documentUploadDetailModel.getIdIssueDate()))<0)

								{
									System.out.println(date+"_"+documentUploadDetailModel.getIdIssueDate()+"issue date must be erlier than expiry date");
									
									errors.rejectValue("documentUploadDetail[" + index + "].idIssueDate", "idIssueDate.validation."+documentUploadDetailModel.getDtype());

								}
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				index++;}
			

			} else if (target instanceof EntityInfoModel) {
				EntityInfoModel user = (EntityInfoModel) target;
				if (user != null && user.getPhone() != null && user.getAlternatePhone() != null) {
					if (user.getPhone().equals(user.getAlternatePhone())) {
						errors.rejectValue("phone", "phone.validation");
					}
				}
			} else {

				AccountInfoModel user = (AccountInfoModel) target;
			}

		}
	}
}
