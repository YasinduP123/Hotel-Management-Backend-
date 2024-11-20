package lk.icet.hotel.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class ImgUpload {

	private ImgUpload(){}
	public static String saveImg(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

		Path uploadPath = Paths.get("E:\\Project_Intelij\\Frame Works (Spring Boot)\\Hotel\\Hotel_Management_System\\src\\main\\resources\\images"+uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			return String.valueOf(filePath);
		}catch (IOException ioException){
			log.info(ioException.getMessage());
		}

		return null;
	}

}

