package pb360.data.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pb360.data.entity.RestApi;

public interface RestApiRepository extends MongoRepository<RestApi, String>,PagingAndSortingRepository<RestApi, String> {
	
	public Integer countByrestId();
	
//	public List<RestApi> findByRestIdContaining(String restId);
	public List<RestApi> findByRestId(String restId);
	public List<RestApi> findByRestId(String filters, PageRequest pageRequest);

	
}
