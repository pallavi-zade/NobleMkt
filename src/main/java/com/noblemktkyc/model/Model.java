package com.noblemktkyc.model;

import java.beans.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @Type(value = PersonalInfoModel.class, name = "PersonalInfo"),
		@Type(value = AccountInfoModel.class, name = "AccountInfo"),
		@Type(value = EntityInfoModel.class, name = "EntityInfo") })
/*
 * public abstract class Model {
 * 
 * @JsonProperty(value = "type") private String type;
 * 
 * private String userName;
 * 
 * 
 * 
 * public String getType() { return type; }
 * 
 * public void setType(String type) { this.type = type; }
 * 
 * public String getUserName() { return userName; }
 * 
 * public void setUserName(String userName) { this.userName = userName; }
 * 
 * }
 */
public interface Model {
	@JsonIgnore
	@JsonProperty(value = "type")
	String getType();
	
	void setType(String type);

	String getUserName();

	void setUserName(String userName);

}
