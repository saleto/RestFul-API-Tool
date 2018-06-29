package pb360.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pb360.data.entity.UserEntity;
import pb360.data.repository.UserRepository;
import pb360.model.MessageObject;
import pb360.model.UserModel;
import pb360.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageObject register(UserModel userModel) {
		MessageObject messageObject = new MessageObject();
		messageObject.setType("Create User");
<<<<<<< HEAD
		messageObject.setData("Can't create User");
		messageObject.setDatetime(new Date());
		if (userRepository.findOneByPassword(userModel.getUsername(), userModel.getPassword()) == null) {
			UserEntity user = new UserEntity();
			if (userModel.getUsername().equals("") || userModel.getPassword().equals("")) {
				messageObject.setData("Username and Password must be not blank!");
			} else if (userModel.getPassword().length() < 8) {
				messageObject.setData("Password must be greater than or equal to 8 characters!");
=======
		messageObject.setData("Cannot create User");

		if (userRepository.findOneByPassword(userModel.getUsername(), userModel.getPassword()) == null) {
			UserEntity user = new UserEntity();
			if (userModel.getPassword().length() < 8) {
				messageObject.setData("Password must be greater than or equal to 8 characters!");
			} else if (userModel.getUsername() == null || userModel.getPassword() == null) {
				messageObject.setData("Please! Enter your username and password!");
>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b
			} else {
				user.setUsername(userModel.getUsername());
				user.setPassword(userModel.getPassword());
				userRepository.save(user);
<<<<<<< HEAD
				messageObject.setData("Create User successful: " + userModel.getUsername());
			}
		}

=======
				messageObject.setData("Create User successful: " + userModel.getUsername() + "\n"
						+ "Please! Login with your account has just registered.");
			
			}
		}
		messageObject.setDatetime(new Date());
>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b
		return messageObject;
	}

	@Override
<<<<<<< HEAD
	public MessageObject login(String username, UserModel user) {
		MessageObject messageObject = new MessageObject();
		messageObject.setType("");
		UserEntity userEntity = userRepository.findOneByUsername(username);
		if (userEntity != null) {
			if (username.equalsIgnoreCase(userEntity.getUsername())
					&& user.getPassword().equals(userEntity.getPassword())) {
				messageObject.setData("Login User successful: " + username);
			} else if (!(username.equals(userEntity.getUsername()))) {
				messageObject.setData("Invalid Username");
			} else if (!user.getPassword().equals(userEntity.getPassword())) {
				messageObject.setData("Invalid Password!");
			} else if (username.equals(userEntity.getUsername())
					&& !user.getPassword().equals(userEntity.getPassword())) {
				messageObject.setData("Invalid Password");
=======
	public MessageObject login(String username, UserModel userModel) {
		MessageObject messageObject = new MessageObject();
		UserEntity user = new UserEntity();
		UserEntity userEntity = userRepository.findOneByUsername(username);
		if (userEntity == null) {
			if (username.equalsIgnoreCase(user.getUsername()) && userModel.getPassword().equals(user.getPassword())) {
				messageObject.setType("Success!");
				messageObject.setData("Login User successful: " + username);
			} else if (username.equalsIgnoreCase(user.getUsername())
					&& !(userModel.getPassword().equals(user.getPassword()))) {
				messageObject.setData("Invalid Password!");
			} else if (!(username.equalsIgnoreCase(user.getUsername()))) {
				messageObject.setData("Invalid Username!");
>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b
			}
		}
		messageObject.setDatetime(new Date());
		return messageObject;

	}

}
