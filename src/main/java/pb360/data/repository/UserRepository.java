package pb360.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pb360.data.entity.UserEntity;
import pb360.model.UserModel;

public interface UserRepository extends MongoRepository<UserEntity, String> {

	public UserEntity findOneByPassword(String username, String password);

	public UserEntity findOneByUsername(String username);

}
