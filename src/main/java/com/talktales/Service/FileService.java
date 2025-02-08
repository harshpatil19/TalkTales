package com.talktales.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.talktales.DTO.PostDTO;

public interface FileService {
	String uploadPostImage(String path, MultipartFile file)throws IOException;
	InputStream getResource(String path, String fileName) throws FileNotFoundException;

}
