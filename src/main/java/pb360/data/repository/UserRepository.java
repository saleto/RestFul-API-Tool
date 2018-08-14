package pb360.data.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pb360.data.entity.UserEntity;

public interface UserRepository
		extends MongoRepository<UserEntity, String>, PagingAndSortingRepository<UserEntity, String> {

	public UserEntity findOneByPassword(String username, String password);

	public UserEntity findOneByUsername(String username);

	public UserEntity findByUsername(String username);

	public List<UserEntity> findByUsername(String filters, PageRequest pageRequest);

}
