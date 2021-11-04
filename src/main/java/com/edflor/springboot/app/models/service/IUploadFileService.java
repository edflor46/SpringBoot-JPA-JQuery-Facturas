package com.edflor.springboot.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public Resource load(String filename) throws MalformedInputException, MalformedURLException;
	
	public String copy(MultipartFile file) throws IOException;
	
	public boolean delete(String filename);
	
	public void deleteAll();
	
	public void init() throws IOException;

}
