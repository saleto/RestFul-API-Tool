package pb360.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public List<UserEntity> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public boolean doesExistByUsername(String username) {
		return userRepository.findByUsername(username) == null;
	}

	@Override
	public List<UserModel> getUsers(int page, int limit) {
		List<UserModel> returnValue = new ArrayList<>();
		PageRequest pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> users = userRepository.findAll(pageableRequest);
		List<UserEntity> userEntities = users.getContent();

		for (UserEntity userEntity : userEntities) {
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(userEntity, userModel);
			returnValue.add(userModel);
		}
		return returnValue;
	}

	@Override
	public Object[] getPager(int totalPages, int currentPage, int pageSize) {
		int startPage;
		int endPage;
		currentPage = 1;
		pageSize = 2;
		totalPages = (int) Math.ceil(totalPages / pageSize);
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		if (totalPages <= 10) {
			// less than 10 total pages so show all
			startPage = 1;
			endPage = totalPages;
		} else {
			// more than 10 total pages so calculate start and end pages
			if (currentPage <= 6) {
				startPage = 1;
				endPage = 10;
			} else if (currentPage + 4 >= totalPages) {
				startPage = totalPages - 9;
				endPage = totalPages;
			} else {
				startPage = currentPage - 5;
				endPage = currentPage + 4;
			}
		}
		int startIndex;
		startIndex = (currentPage - 1) * pageSize;
		int endIndex;
		endIndex = Math.min(startIndex + pageSize - 1, totalPages - 1);
		
		return null;
	}

}
