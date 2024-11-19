package lk.icet.hotel.controller;

import jakarta.mail.MessagingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import lk.icet.hotel.dto.User;
import lk.icet.hotel.service.UserService;
import lk.icet.hotel.util.ImgUpload;
import lk.icet.hotel.util.StoreOtp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	final UserService userService;

	final StoreOtp otp;

	@PostMapping("/save")
	public void save(@RequestBody User user){

		userService.save(user);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id){
		userService.delete(id);
	}


	@GetMapping("/all")
	public List<User> get(){
		return userService.getAll();
	}

	@GetMapping("/validate")
	public List<User> validate(@RequestParam(required = false) String password , String userName){
		if(password != null && userName != null){
			return userService.validateUserLogin(userName,password);
		}
		return Collections.emptyList();
	}

	@GetMapping("/generate-otp/{email}")
	public String sendOtp(@PathVariable String email){
		try {
			return userService.sendEmail(email);
		} catch (MessagingException e) {
			log.error(String.valueOf(e));
		}
		return null;
	}

	@GetMapping("/get-otp")
	public String getOtp(){
		return otp.getOtp();
	}

}
