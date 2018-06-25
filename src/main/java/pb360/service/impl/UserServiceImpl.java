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
		messageObject.setType("CreateUser");
		messageObject.setData("Cannot create User");

		if (userRepository.findOneByPassword(userModel.getUsername(), userModel.getPassword()) == null) {
			UserEntity user = new UserEntity();
			user.setUsername(userModel.getUsername());
			user.setPassword(userModel.getPassword());
			userRepository.save(user);

			messageObject.setData("Create User successful: " + userModel.getUsername());
		}

		messageObject.setDatetime(new Date());

		return messageObject;
	}

	@Override
	public MessageObject login(String username, String password) {
		MessageObject messageObject = new MessageObject();
		messageObject.setData("Login User");
		messageObject.setType("Get information user");
		if (userRepository.findOneByUsernameAndPassword(username, password) == null) {
			UserEntity user = new UserEntity();
			if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				messageObject.setData("Login User successful: " + username);
			} else if (username.equals(user.getUsername()) && !password.equals(user.getPassword())) {
				messageObject.setData("Invalid Password!");
			} else if (!username.equals(user.getUsername())) {
				messageObject.setData("Invalid Username!");
			}
		}
		messageObject.setDatetime(new Date());

		return messageObject;

	}

}
