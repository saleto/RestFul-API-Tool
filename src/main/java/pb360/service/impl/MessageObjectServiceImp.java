package pb360.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pb360.data.repository.MessageObjectRepository;
import pb360.model.MessageObject;
import pb360.service.MessageObjectService;

@Service
public final class MessageObjectServiceImp implements MessageObjectService {

	private final MessageObjectRepository repository;

	@Autowired
	MessageObjectServiceImp(MessageObjectRepository repository) {
		this.repository = repository;
	}

	public List<MessageObject> getMessageObject() {
		return repository.findAll();
	}

	@Override
	public MessageObject ThreeData() {
		// TODO Auto-generated method stub
		MessageObject MO = new MessageObject();

		Date datetime = new Date();
		MO.setDatetime(datetime);

		MO.getType();
		MO.getData();

		return MO;
	}

}
