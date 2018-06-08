package pb360.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;

import com.fasterxml.jackson.databind.ObjectMapper;


import pb360.model.JsonDataModel;
import pb360.model.MessageObject;
import pb360.model.RestAPI;
import pb360.model.embedded.Annotations;
import pb360.model.embedded.JsonNode;
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
		readJson(link);
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
		String inputStr;
		StringBuilder stringBuilder = new StringBuilder();


		try {
			File initialFile = new File(link);
			InputStream targetStream = new FileInputStream(initialFile);
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(targetStream));
			ObjectMapper mapper = new ObjectMapper();
//			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			String line = "";
			
			while (( line = buffReader.readLine()) != null) {
				stringBuilder.append(line);
			} 
			targetStream.close();
			buffReader.close();
				
		
			
			inputStr = stringBuilder.toString();
			inputStr = inputStr.replaceAll("\\s","");
			System.out.println(inputStr);

			
			
            
//            jsonData = mapper.readValue(inputStr, JsonDataModel.class);
            
            String title = jsonData.getTitle();
            System.out.println("title = " + "Controller Name");
//            Annotations anno = jsonData.getModel().getclassAnnotation().get(0);
            
           
//            	System.out.println("content: " +anno.getAnnotations().get(0).getAnnotationContent());
//            	System.out.println("start: " +anno.getAnnotations().get(0).getAnnotationStarts());
//            	System.out.println("end: " +anno.getAnnotations().get(0).getAnnotationEnds());
            
            
//            System.out.println(jsonData.getModel().getClassAnnotationList().);
//            System.out.println(node.get("title").asText());
            
//            write2Java(inputStr, "testFile");
			
			
			

			

		}  catch(IOException e)
		{
			e.printStackTrace();
		}	
		
		//write to java file.
		
		
		
	}
	
	public void write2Java(String stringData, String fileName)
	{
		try {
			
			File file = new File(fileName+".java");
			if (file.exists()) {
			      file.delete();
			 }
			 file.createNewFile();
			
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(stringData);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
