package com.talktales.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.talktales.Service.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadPostImage(String path, MultipartFile file) throws IOException {
		 String originalFileName = file.getOriginalFilename();
		String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
		 String filePath = path + File.separator + uniqueFileName;
		File f= new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}  
	    Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
		
		return uniqueFileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
