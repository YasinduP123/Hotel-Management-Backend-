package lk.icet.hotel.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import lk.icet.hotel.dto.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserService {

	void save(User user);

	List<User> getAll();

	List<User> validateUserLogin(String userName, String password);

	void delete(Long id);
	String sendEmail(String email) throws MessagingException;
	Message prepareMessage(Session session, String myEmail, String email, String msg);

	List<User> findByEmail(String email);

	void update(User user);
}
