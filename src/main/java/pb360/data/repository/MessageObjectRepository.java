package pb360.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pb360.model.MessageObject;

public interface MessageObjectRepository extends MongoRepository<MessageObject, String> {

}