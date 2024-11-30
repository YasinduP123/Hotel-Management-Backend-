package lk.icet.hotel.service.impl;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lk.icet.hotel.dto.User;
import lk.icet.hotel.entity.UserEntity;
import lk.icet.hotel.repository.UserRepository;
import lk.icet.hotel.service.UserService;
import lk.icet.hotel.util.Password;
import lk.icet.hotel.util.StoreOtp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	final UserRepository userRepository;
	final ModelMapper mapper;
	final Random random;
	final Password secretCode;
	final StoreOtp storeOtp;

	@Override
	public void save(User user) {
		userRepository.save(mapper.map(user, UserEntity.class));
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(userEntity -> userList.add(mapper.map(userEntity, User.class)));
		return userList;
	}

	@Override
	public List<User> validateUserLogin(String userName,String password) {
		List<User> userList = new ArrayList<>();
		userRepository.findUser(userName,password).forEach(userEntity -> userList.add(mapper.map(userEntity, User.class)));
		return userList;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public String sendEmail(String email) throws MessagingException {

		log.info(email);
		int randNum = 100000 + random.nextInt(900000);
		log.info("Random 6-digit number: " + randNum);
		String otp = String.valueOf(randNum);
		storeOtp.setOtp(otp);
		// Generate a 6-digit OTP

		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");

		String myEmail = "yasindupathiraja10@gmail.com";
		String password = secretCode.getSecretCode();

		Session session = Session.getInstance(properties, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(myEmail, password);
		}
	});

		Message message = prepareMessage(session , myEmail ,email,otp);

	try {
		if (message != null){
			Transport.send(message);
			return otp;
		}
		}catch (SendFailedException e){
			return null;
		}
		return null;
	}



	public Message prepareMessage(Session session, String myEmail, String email, String msg) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{
					new InternetAddress(email)
			});

			String htmlContent = "<!DOCTYPE html>" +
					"<html>" +
					"<head>" +
					"    <style>" +
					"        body {" +
					"            font-family: Arial, sans-serif;" +
					"            margin: 0;" +
					"            padding: 0;" +
					"            background-color: #f4f4f4;" +
					"        }" +
					"        .email-container {" +
					"            max-width: 600px;" +
					"            margin: 20px auto;" +
					"            background-color: #ffffff;" +
					"            border-radius: 10px;" +
					"            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);" +
					"            overflow: hidden;" +
					"        }" +
					"        .header {" +
					"            background-color: #1e2a78;" +
					"            color: #ffffff;" +
					"            text-align: center;" +
					"            padding: 20px 10px;" +
					"        }" +
					"        .header h1 {" +
					"            margin: 0;" +
					"            font-size: 28px;" +
					"        }" +
					"        .body {" +
					"            padding: 20px;" +
					"            color: #ffffff;" +
					"            background-color: black;" +
					"            line-height: 1.6;" +
					"        }" +
					"        .otp-box {" +
					"            display: inline-block;" +
					"            background-color: #28005f;" +
					"            border: 1px dashed #ffffff;" +
					"            color: #ffffff;" +
					"            font-weight: bold;" +
					"            font-size: 24px;" +
					"            padding: 10px 20px;" +
					"            border-radius: 8px;" +
					"            margin: 10px 0;" +
					"        }" +
					"        .footer {" +
					"            background-color: #1e2a78;" +
					"            color: #ffffff;" +
					"            text-align: center;" +
					"            padding: 10px;" +
					"            font-size: 14px;" +
					"        }" +
					"        .footer a {" +
					"            color: #ffffff;" +
					"            text-decoration: underline;" +
					"        }" +
					"        .footer a:hover {" +
					"            color: #cccccc;" +
					"        }" +
					"    </style>" +
					"</head>" +
					"<body>" +
					"    <div class=\"email-container\">" +
					"        <div class=\"header\">" +
					"            <h1>Welcome to Luxury Grand Hotel</h1>" +
					"            <p>Your exclusive stay experience awaits!</p>" +
					"        </div>" +
					"        <div class=\"body\" style=\"color:#ffffff\">" +
					"            <p style=\"color:#ffffff\">Dear Guest,</p>" +
					"            <p style=\"color:#ffffff\">We are excited to have you with us. To complete your verification, please use the One-Time Password (OTP) below:</p>" +
					"            <p style=\"color:#ffffff\" class=\"otp-box\">" + msg + "</p>" +
					"            <p style=\"color:#ffffff\">If you did not request this OTP, please contact us immediately.</p>" +
					"            <p style=\"color:#ffffff\">Warm regards,<br>The Luxury Grand Hotel Team</p>" +
					"        </div>" +
					"        <div class=\"footer\">" +
					"            <p>&copy; 2024 Luxury Grand Hotel | All Rights Reserved</p>" +
					"            <p>Need help? <a href=\"mailto:yasindupathiraja10@gmail.com\">Contact Us</a></p>" +
					"        </div>" +
					"    </div>" +
					"</body>" +
					"</html>";
			message.setContent(htmlContent, "text/html");
			message.setSubject("Luxury Hotel (PVT) LTD");


			return message;

		}catch (Exception e){
			Logger.getLogger(StoreOtp.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
	}

	@Override
	public List<User> findByEmail(String email) {
		List<User> users = new ArrayList<>();
		userRepository.findByEmail(email).forEach(userEntity -> users.add(mapper.map(userEntity, User.class)));
		return users;
	}

	@Override
	public void update(User user) {
		userRepository.save(mapper.map(user, UserEntity.class));
	}


}