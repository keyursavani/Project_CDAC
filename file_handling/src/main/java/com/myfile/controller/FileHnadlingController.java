package com.myfile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myfile.entity.FileData;
import com.myfile.services.FileDataServicesImpl;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/myfile")
@AllArgsConstructor
public class FileHnadlingController {
	
	private FileDataServicesImpl fileDataServicesImpl;
	
	@GetMapping("/test")
	public ResponseEntity<?> test(){
		return ResponseEntity.ok(List.of(1,2,3,4));
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(MultipartFile file, @RequestPart("data") FileData data) throws IllegalStateException, IOException{
       System.out.println(file.getOriginalFilename());
       System.out.println(data.getId());
       System.out.println(data.getName());
		return ResponseEntity.ok(fileDataServicesImpl.uploadImage(file));		
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException{
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(fileDataServicesImpl.downlodImage(fileName));		
	}
	
	@PostMapping("/upload-multiple")
	public ResponseEntity<?> uploadMultipleImage(@RequestPart("file") MultipartFile[] file) throws IllegalStateException, IOException{
          System.out.println(file[0].getOriginalFilename());
          System.out.println(file[1].getOriginalFilename());
		return ResponseEntity.ok("");		
	}
	

}
