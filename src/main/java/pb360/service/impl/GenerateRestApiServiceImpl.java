package pb360.service.impl;

import java.util.Date;

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

		return getMessage;
	}

	@Override
	public MessageObject searchRestApiData() {
		MessageObject search = new MessageObject();
		search.setData("Search RestApi data successful");
		search.setType("GET ALL");

		return search;
	}

	@Override
	public MessageObject createRestApiData() {
		MessageObject post = new MessageObject();
		post.setData("“generate restapi files successful");
		post.setType("POST");
		post.setDatetime(getCurrentDateTime().getDate());

		return post;

	}

	@Override
	public MessageObject updateRestApiData() {
		MessageObject patch = new MessageObject();
		patch.setData("updated restapi files successful");
		patch.setType("PATCH");

		return patch;
	}

	public MessageObject deleteRestApiData() {
		MessageObject delete = new MessageObject();
		delete.setData("delete restapi files successful");
		delete.setType("DELETE");

		return delete;
	}

	private MessageObject getCurrentDateTime() {
		MessageObject date = new MessageObject();
		Date datetime = new Date();
		date.setDatetime(datetime);

		return date;
	}
}
