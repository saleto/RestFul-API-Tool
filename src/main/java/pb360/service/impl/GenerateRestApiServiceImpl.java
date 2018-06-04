package pb360.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.util.JSON;

import pb360.model.JsonDataModel;
import pb360.model.MessageObject;
import pb360.model.RestAPI;
import pb360.service.GenerateRestApiService;

@Service
public final class GenerateRestApiServiceImpl implements GenerateRestApiService {
	public String ControllerName;
	public String link = "D:\\restapi\\JSON_Sample.json";

	@Override
	public MessageObject getRestApiData(String id) {
		MessageObject getMessage = new MessageObject();
		getMessage.setData("Get RestApi data successful: " + id);
		getMessage.setType("GET");
		getMessage.setDatetime(getCurrentDateTime().getDatetime());
		readJson(link);

		return getMessage;
	}

	@Override
	public List<MessageObject> searchRestApiData() {
		List<MessageObject> messageObjectList = new ArrayList<MessageObject>();

		MessageObject search = new MessageObject();
		search.setData("Search RestApi data successful");
		search.setType("GET ALL");
		search.setDatetime(getCurrentDateTime().getDatetime());

		messageObjectList.add(search);
		return messageObjectList;
	}

	@Override
	public MessageObject createRestApiData(RestAPI restApi) {
		MessageObject post = new MessageObject();
		post.setData("generate restapi files successful");
		post.setType("POST");
		post.setDatetime(getCurrentDateTime().getDatetime());
		return post;

	}

	@Override
	public MessageObject updateRestApiData(String restId) {
		MessageObject patch = new MessageObject();
		patch.setData("updated restapi files successful: " + restId);
		patch.setType("PATCH");
		patch.setDatetime(getCurrentDateTime().getDatetime());

		return patch;
	}

	public MessageObject deleteRestApiData(String restId) {
		MessageObject delete = new MessageObject();
		delete.setData("delete restapi files successful: " + restId);
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


	private void readJson(String link) {

		JsonDataModel jsonData = new JsonDataModel();
		String inputStr= "";

		try {
			File initialFile = new File(link);
			InputStream targetStream = new FileInputStream(initialFile);
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(targetStream));
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			buffReader.readLine();
			while ((inputStr = buffReader.readLine()) != null) {
//				System.out.println(inputStr); doc duoc 
				
				
				JsonParser jsonParser = new JsonFactory().createParser(mapper.writeValueAsString(inputStr));
//				System.out.println(jsonParser);
//				while(!jsonParser.isClosed()){
//				    JsonToken jsonToken = jsonParser.nextToken();
//
//				    System.out.println("jsonToken = " + jsonToken);
//				}
//				ObjectNode node = mapper.readValue(jsonParser, ObjectNode.class);
//				System.out.println(node);

//				if (node != null && node.has("title")) {															
//					node.get("title").asText();
//				} 
				
				JsonDataModel data = mapper.readValue(inputStr, JsonDataModel.class);
				
				
				System.out.println(data);
		
			}
			
			

		}  catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
}
