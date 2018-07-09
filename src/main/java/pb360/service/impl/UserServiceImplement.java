package pb360.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pb360.data.entity.UserEntity;
import pb360.data.repository.UserRepository;
import pb360.model.MessageObject;
import pb360.model.UserModel;
import pb360.service.UserService;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageObject register(UserModel userModel) {
		MessageObject messageObject = new MessageObject();
		messageObject.setType("Create User");
		messageObject.setData("Can't create User");
		messageObject.setDatetime(new Date());
		UserEntity userChecking = userRepository.findOneByUsername(userModel.getUsername());
		if (userRepository.findOneByPassword(userModel.getUsername(), userModel.getPassword()) == null) {
			UserEntity user = new UserEntity();
			if (userChecking != null) {
				if (userModel.getUsername().equals(userChecking.getUsername())) {
					messageObject.setData("Username must be unique!");
				}
			} else if (userModel.getUsername().equals("") || userModel.getPassword().equals("")) {
				messageObject.setData("Username and Password must be not blank!");
			} else if (userModel.getPassword().length() < 8) {
				messageObject.setData("Password must be greater than or equal to 8 characters!");
			} else {
				user.setUsername(userModel.getUsername());
				user.setPassword(userModel.getPassword());
				userRepository.save(user);
				messageObject.setData("Create User successful: " + userModel.getUsername() + "\n"
						+ "Please! Login with your account has just registered.");

			}
		}
		messageObject.setDatetime(new Date());

		return messageObject;
	}

	@Override
	public MessageObject login(String username, UserModel user) {
		MessageObject messageObject = new MessageObject();
		messageObject.setType("Login User");
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
			}
		} else {
			messageObject.setData("Invalid Username");
		}
		messageObject.setDatetime(new Date());
		return messageObject;

	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

}
