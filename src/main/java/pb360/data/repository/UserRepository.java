package pb360.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pb360.data.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

	public UserEntity findOneByPassword(String username, String password);
	public UserEntity findOneByUsernameAndPassword(String username, String password);

}
