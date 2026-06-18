package com.agrolink.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;





@RestController
@RequestMapping("/api/files")
@CrossOrigin("*")
public class FileUploadController {

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@PostMapping(
            value = "/upload",
            consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
    )
	
	public ResponseEntity<?> uploadFile(
			@RequestParam("file")
			MultipartFile file)
	        throws IOException
	        {
		    String fileName = System.currentTimeMillis()
		    		+"_"
		    		+file.getOriginalFilename();
	        
	
	java.nio.file.Path path = Paths.get(
			uploadDir,
			fileName);
	
	Files.createDirectories(path.getParent());
	
	Files.write(path, file.getBytes());
	
	String imageUrl =
			"https://agrolink-backend-k4eo.onrender.com/uploads/"+fileName;
	
	return ResponseEntity.ok(imageUrl);
	
	
	
	
	        }
			
	
	
	
}
