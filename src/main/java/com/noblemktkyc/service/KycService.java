package com.noblemktkyc.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;


/**
@author
interface for service method
 * 
 */
public interface KycService {
	
    void saveObject(Object user,String userDirectory,String infoType,String userName);
    File convert(MultipartFile file, String userName, String path, String fileName);
}
