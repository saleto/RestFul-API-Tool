package pb360.service.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import pb360.model.MessageObject;
import pb360.service.GenerateRestApiService;

@Service
public final class GenerateRestApiServiceImpl implements GenerateRestApiService {

	@Override
	public MessageObject getRestApiData(String id) {
		MessageObject getMessage = new MessageObject();
		getMessage.setData("Get RestApi data successful: " + id);
		getMessage.setType("GET");
		getMessage.setDatetime(getCurrentDateTime().getDatetime());

		return getMessage;
	}

	@Override
	public List<MessageObject> searchRestApiData() {
		List<MessageObject> messageObjectList = new ArrayList<>();
		MessageObject search = new MessageObject();
		search.setData("Search RestApi data successful");
		search.setType("GET ALL");
		search.setDatetime(getCurrentDateTime().getDatetime());

		messageObjectList.add(search);
		return messageObjectList;
	}

	@Override
	public MessageObject createRestApiData() {
		MessageObject post = new MessageObject();
		post.setData("“generate restapi files successful");
		post.setType("POST");
		post.setDatetime(getCurrentDateTime().getDatetime());

		return post;

	}

	@Override
	public MessageObject updateRestApiData() {
		MessageObject patch = new MessageObject();
		patch.setData("updated restapi files successful");
		patch.setType("PATCH");
		patch.setDatetime(getCurrentDateTime().getDatetime());

		return patch;
	}

	public MessageObject deleteRestApiData() {
		MessageObject delete = new MessageObject();
		delete.setData("delete restapi files successful");
		delete.setType("DELETE");
		delete.setDatetime(getCurrentDateTime().getDatetime());

		return delete;
	}

	private MessageObject getCurrentDateTime() {
		MessageObject date = new MessageObject();
		Date datetime = new Date();
		date.setDatetime(datetime);

		return date;
	}
}
