package pb360.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pb360.data.entity.RestApi;

public interface RestApiRepository extends MongoRepository<RestApi, String> {

}
