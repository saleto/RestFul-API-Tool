package pb360.service;

import pb360.model.MessageObject;
import pb360.model.UserModel;

public interface UserService {

	public MessageObject register(UserModel userModel);

<<<<<<< HEAD
	public MessageObject login(String username, UserModel user);
=======
	public MessageObject login(String username, UserModel userModel);
>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b
}
