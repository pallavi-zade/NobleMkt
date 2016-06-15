package com.noblemktkyc.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.noblemktkyc.fileoperation.*;

/**
@author 
java class for service implementation
 * 
 */
@Service("KycService")
@Transactional
public class KycServiceImpl implements KycService {
	
	FileOperation fileOperation = new FileOperation();

	public KycServiceImpl() {

		System.out.println("==================user service======================");
	}

	//saving any  kyc detail to text file
	@Override
	public void saveObject(Object user, String userDirectory, String infoType, String userName) {
		fileOperation.saveObject(user, userDirectory, infoType, userName);
	}

	@Override
	public File convert(MultipartFile file, String userName, String path, String fileName) {
	
		return fileOperation.convert(file, userName, path, fileName);
	}

}
