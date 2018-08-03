package pb360.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import pb360.data.entity.UserEntity;
import pb360.model.MessageObject;
import pb360.model.UserModel;

public interface UserService {

	public MessageObject register(UserModel userModel);

	public MessageObject login(String username, UserModel userModel);

	public List<UserEntity> findAllUser(Iterable<String> filters, int pageNumber, int pageSize);

	public boolean doesExistByUsername(String username);

	public PageRequest gotoPage(int page);

}
