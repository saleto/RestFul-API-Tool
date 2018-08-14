package pb360.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pb360.data.entity.UserEntity;
import pb360.data.repository.UserRepository;
import pb360.model.MessageObject;
import pb360.model.UserModel;
import pb360.service.UserService;

@Service
public class UserServiceImplement implements UserService {

	public static final int PAGE_NUMBER = 0;
	public static final int PAGE_SIZE = 2;

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
				messageObject.setData("Create User successful: " + userModel.getUsername());
				user.setUsername(userModel.getUsername());
				user.setPassword(userModel.getPassword());
				userRepository.save(user);

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
				messageObject.setData("Login User successful:" + username);
			} else if (!(username.equals(userEntity.getUsername()))) {
				messageObject.setData("Invalid Username");
			} else if (!user.getPassword().equals(userEntity.getPassword())) {
				messageObject.setData("Invalid Password!");
			} else if (username.equals(userEntity.getUsername())
					&& !user.getPassword().equals(userEntity.getPassword())) {
				messageObject.setData("Invalid Password");
			}
		} else {
			if (doesExistByUsername(username)) {
				messageObject.setData("Account is not exist!");
			} else {
				messageObject.setData("Invalid Username");
			}
		}
		messageObject.setDatetime(new Date());
		return messageObject;

	}

	@Override
	public boolean doesExistByUsername(String username) {
		return userRepository.findByUsername(username) == null;
	}

	@Override
	public List<UserEntity> findAllUsers(String filters, Integer pageNumber, Integer pageSize) {

		if (filters != null) {
			if (pageNumber == null) {
				pageNumber = PAGE_NUMBER;
			}
			if (pageSize == null) {
				pageSize = PAGE_SIZE;
			}
			long count = userRepository.count();
			long pages = (int) (count / pageSize);

			for (int i = 0; i < pages;) {
				List<UserEntity> listUsers = userRepository.findByUsername(filters, PageRequest.of(i, pageSize));
				return listUsers;
			}
		}
		return null;

	}

}
