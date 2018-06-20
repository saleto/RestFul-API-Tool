package pb360.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pb360.data.entity.User;
import pb360.data.repository.UserRepository;
import pb360.model.UserModel;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean register(UserModel userModel) {

		if (userRepository.findOneByPassword(userModel.getPassword()) == null) {
			User user = new User();
			user.setUsername(userModel.getUsername());
			user.setPassword(userModel.getPassword());
			//userRepository.save(user);
			return true;
		}
		return false;

	}
}
