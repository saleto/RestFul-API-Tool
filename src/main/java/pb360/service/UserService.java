package pb360.service;

import pb360.model.MessageObject;
import pb360.model.UserModel;

public interface UserService {

	public MessageObject register(UserModel userModel);

	public MessageObject login(String username, UserModel userModel);
	

}
