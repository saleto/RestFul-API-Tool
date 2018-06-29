package pb360.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pb360.data.entity.UserEntity;
import pb360.model.UserModel;

public interface UserRepository extends MongoRepository<UserEntity, String> {

	public UserEntity findOneByPassword(String username, String password);
<<<<<<< HEAD

=======
>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b
	public UserEntity findOneByUsername(String username);

}
