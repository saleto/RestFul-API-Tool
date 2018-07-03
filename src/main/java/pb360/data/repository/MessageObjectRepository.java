package pb360.data.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pb360.data.entity.RestApi;
import pb360.model.MessageObject;

public interface MessageObjectRepository extends MongoRepository<RestApi, String> {
	
//	public RestApi findBymessageNumber(String messageNumber);
//	public RestApi findFirstByOrderByrestIdDesc();
	public int countByrestId();
	

	
}