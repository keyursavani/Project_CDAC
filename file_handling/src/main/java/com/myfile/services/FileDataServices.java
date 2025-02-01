package com.myfile.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileDataServices{
	 public String uploadImage(MultipartFile file) throws IllegalStateException, IOException;
	public byte[] downlodImage(String fileName) throws IOException;
}
