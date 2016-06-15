package com.noblemktkyc.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.noblemktkyc.model.CustomerValidator;
import com.noblemktkyc.model.Model;
import com.noblemktkyc.service.KycService;

/**
 * @author j java rest services
 * 
 * 
 */
@RestController
public class ControllerClass {

	@Value("${path}") // get value from property file
	private String path;
	@Autowired
	private KycService kycService;
	@Autowired
	Environment env;
	@Autowired
	CustomerValidator customerValidator;

	public ControllerClass() {

		System.out.println("============inside controllre==================");
		System.out.println("path==========" + path);
	}

	// Saving user data for all sections
	@SuppressWarnings("unused")	@RequestMapping(value = "/userInfo/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<List<String>> saveData(@Valid @RequestBody Model user, BindingResult result,
			UriComponentsBuilder ucBuilder, HttpServletRequest request) {
		List<String> errorList = new ArrayList<>();
      try {
		
		   customerValidator.validate(user, result);
		
			System.out.println("path================>>" + path);
			if (result.hasErrors()) {
				for (Object object : result.getAllErrors()) {
					if (object instanceof FieldError) {
						FieldError fieldError = (FieldError) object;
						errorList.add(env.getProperty(fieldError.getField()));
						System.out.println(env.getProperty(fieldError.getField()));

					}
					 if(object instanceof ObjectError) {
					        ObjectError objectError = (ObjectError) object;
					        errorList.add(env.getProperty(objectError.getCode()));
					        System.out.println(objectError.getCode());
					        System.out.println(env.getProperty(objectError.getCode()));
					    }

				}
			}
			System.out.println("path:::::==========" + path);
			System.out.println("+++++inside universal detail==>user is" + user);
			if (user != null && user.getUserName() != null) {

				kycService.saveObject(user, path, user.getType(), user.getUserName());

			}
			System.out.println("++++inside universal of controller=======text to file" + user.getType());
		} catch (Exception e) {

			System.out.println("Exception in createUser " + e);
			e.printStackTrace();
			return new ResponseEntity<List<String>>(errorList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        if(errorList!=null)
		return new ResponseEntity<List<String>>(errorList, HttpStatus.OK);
        else
        return new ResponseEntity<List<String>>(errorList,HttpStatus.OK);
	}

	// for saving uploaded file
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadFileHandler(MultipartHttpServletRequest request, HttpServletResponse response,
			HttpServletRequest httpServletRequest, @RequestParam String userName, @RequestParam String newFileName) {
		try {
			System.out.println("++++inside=======file uplode11111");
			// 1. get the files from the request object
			java.util.Iterator<String> itr = request.getFileNames();
			if (itr == null) {
				System.out.println("itr null");
			} else {

				MultipartFile file = request.getFile(itr.next());
				kycService.convert(file, userName, path, newFileName);
			}

		} catch (Exception e) {
			System.out.println("Exception is : " + e);
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}