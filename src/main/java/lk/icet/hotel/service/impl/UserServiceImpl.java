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

			message.setSubject("Luxury Hotel (PVT) LTD");
			message.setText(msg);

			return message;

		}catch (Exception e){
			Logger.getLogger(StoreOtp.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
	}




}