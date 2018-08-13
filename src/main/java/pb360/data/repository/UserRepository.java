package pb360.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pb360.data.entity.UserEntity;

public interface UserRepository
		extends MongoRepository<UserEntity, String>, PagingAndSortingRepository<UserEntity, String> {

	public UserEntity findOneByPassword(String username, String password);

	public UserEntity findOneByUsername(String username);

	public UserEntity findByUsername(String username);

}
