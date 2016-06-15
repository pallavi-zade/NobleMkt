package com.noblemktkyc.fileoperation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileOperation implements Serializable {
	/**
	 * Author: all file related task
	 */
	private static final long serialVersionUID = 1L;

	public FileOperation() {

		System.out.println("=============file opretion");

	}

	// saving any kyc detail of user to text file
	public void saveObject(Object user, String userDirectory, String infoType, String userName) {
		File f = null;

		try {
			if (user != null && userName != null) {
				boolean success = (new File(userDirectory + "\\" + userName)).mkdirs();
				if (success)
					System.out.println("directory is created");
				f = new File(userDirectory + "\\" + userName + "\\" + infoType+"_"  + userName + ".txt");
			}

			System.out.println("getServletContext():::");
			if (user != null && userName != null && infoType != null) {

				f = new File(userDirectory + "\\" + userName + "\\" + infoType+"_" + userName + ".txt");
				System.out.println("getServletContext()::: inside saveObject");
			}

			if (!f.exists())
				f.createNewFile();
		} catch (Exception e) {
			System.out.println("Exception in save operation file exists " + e);
			e.printStackTrace();
		}
		try {

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
			ObjectMapper mapper = new ObjectMapper();
			stream.write(mapper.writeValueAsString(user).getBytes());
			stream.close();

		} catch (Exception e) {
			System.out.println("Exception in save operation " + e);
			e.printStackTrace();
		}

	}

	// processing multipart file
	public File convert(MultipartFile file, String userName, String path, String fileName) {
		File convFile;
		System.out.println("++++inside=======convert file uplode11111");

		boolean success = (new File(path + "\\" + userName)).mkdirs();
		System.out.println(path);
		if (success)
			System.out.println("directory is created");
		if (fileName == null)
			convFile = new File(path + "\\" + userName + "\\" + file.getOriginalFilename());
		else
			convFile = new File(path + "\\" + userName + "\\" + fileName);

		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);

			fos.write(file.getBytes());
			fos.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return convFile;
	}

}
