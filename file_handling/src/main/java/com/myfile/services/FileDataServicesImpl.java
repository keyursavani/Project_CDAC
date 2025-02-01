package com.myfile.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myfile.entity.FileData;
import com.myfile.repository.FileDataRepository;


import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class FileDataServicesImpl implements FileDataServices{
	
	private final String folderPath = "E:/IACSD_August_2024_Batch/10_Final_Project/ImageData/";
    private FileDataRepository fileDataRepository;
    
    @Override
    public String uploadImage(MultipartFile file) throws IllegalStateException, IOException {
    	String filePath = folderPath+file.getOriginalFilename();
    	FileData fileData = new FileData();
    	fileData.setName(file.getOriginalFilename());
    	fileData.setType(file.getContentType());
    	fileData.setFilePath(filePath);
    	if(!fileDataRepository.existsByName(file.getOriginalFilename())) {
        	fileData = fileDataRepository.save(fileData);
        	 file.transferTo(new File(filePath));
    	}else {
    		System.out.println("Else part");
    	}
    	return "Image succesfully uploaded at "+fileData.getName();
    }
    
    @Override
    public byte[] downlodImage(String fileName) throws IOException {
    	FileData fileData = fileDataRepository.findByName(fileName).orElseThrow();
    	byte[] image = Files.readAllBytes(new File(fileData.getFilePath()).toPath());
    	return image;
    }
    
    
}
