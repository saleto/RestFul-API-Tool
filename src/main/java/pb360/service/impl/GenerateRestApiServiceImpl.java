package pb360.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Service;

import pb360.model.JsonDataModel;
import pb360.model.MessageObject;
import pb360.model.RestAPI;
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
		JsonDataModel jData = new JsonDataModel();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(link));

			JSONObject jsonObject = (JSONObject) obj;

			// title
			String title = (String) jsonObject.get("title");
			jData.setTitle(jsonObject.get("title").toString());

			readModel(jsonObject, jData);
			readOption(jsonObject);
			readService(jsonObject);
			readJUnitTest(jsonObject);
			readController(jsonObject);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		

	}
	
	public void readModel(JSONObject jsonObject, JsonDataModel jData) {
		// Model
		JsonNode jNode = new JsonNode();
		JSONArray modelArray = (JSONArray) jsonObject.get("Model");
		for (int i = 0; i < modelArray.size(); i++) {
			JSONObject object = (JSONObject) modelArray.get(i);
//			jData.setModel();
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jNode.setPackages(packageName);
			jNode.setClassHeader(classHeader);
			
			
			
			jData.setModel(jNode);

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int a = 0; a < classAnotation.size(); a++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(a);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");
				String annotationContent = (String) annotation.get("annotationContent");

			}

		}
	}
	
	public void readValidator(JSONObject jsonObject)
	{
		//Validator
		JsonNode jNode = new JsonNode();
		JSONArray validatorArray = (JSONArray) jsonObject.get("Validator");
		for (int i = 0; i < validatorArray.size(); i++) {
			JSONObject object = (JSONObject) validatorArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jNode.setPackages(packageName);
			jNode.setClassHeader(classHeader);
			

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int a = 0; a < classAnotation.size(); a++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(a);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");
				String annotationContent = (String) annotation.get("annotationContent");
				
				System.out.println("\n"+annotationContent);
			}
		
		}
	}
	
	
	public void readOption(JSONObject jsonObject)
	{
		//Options
		JSONArray optionsArray = (JSONArray) jsonObject.get("Options");
		for (int i = 0; i < optionsArray.size(); i++) {
			JSONObject object = (JSONObject) optionsArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int a = 0; a < classAnotation.size(); a++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(a);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");
				String annotationContent = (String) annotation.get("annotationContent");
			}
			
//			JSONArray methods = (JSONArray) object.get("methods");
//			for (int b = 0; b < methods.size(); b++) {
//				JSONObject objectClass = (JSONObject) methods.get(b);
//				JSONObject method = (JSONObject) objectClass.get("method");
//				String headerContent = (String) method.get("header").toString();
//				
//			}
		
		}
	}
	
	public void readService(JSONObject jsonObject)
	{
		//Service
		JSONArray serviceArray = (JSONArray) jsonObject.get("Service");
		for (int i = 0; i < serviceArray.size(); i++) {
			JSONObject object = (JSONObject) serviceArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int a = 0; a < classAnotation.size(); a++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(a);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");
				String annotationContent = (String) annotation.get("annotationContent");
			}
			
			JSONArray methods = (JSONArray) object.get("methods");
			for (int b = 0; b < methods.size(); b++) {
				JSONObject objectClass = (JSONObject) methods.get(b);
				JSONObject method = (JSONObject) objectClass.get("method");
				String headerContent = (String) method.get("header");
			}
		
		}
	}
	
	public void readController(JSONObject jsonObject)
	{
		// Controller
		JSONArray controllerArray = (JSONArray) jsonObject.get("Controller");
		for (int i = 0; i < controllerArray.size(); i++) {
			JSONObject object = (JSONObject) controllerArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int a = 0; a < classAnotation.size(); a++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(a);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");
				String annotationContent = (String) annotation.get("annotationContent");
			}
			List<String> line = new ArrayList();
			JSONArray methods = (JSONArray) object.get("methods");
			for (int b = 0; b < methods.size(); b++) {
				JSONObject objectClass = (JSONObject) methods.get(b);
				JSONObject method = (JSONObject) objectClass.get("method");
				String headerContent = (String) method.get("header");
				JSONArray body = (JSONArray) object.get("body");

				for (int c = 0; c < body.size(); c++) {
					JSONObject lineObject = (JSONObject) body.get(c);
					String LINE = (String) lineObject.get("line");
					line.add(LINE);
				}
			}

		}
	}
	
	
	public void readJUnitTest(JSONObject jsonObject) {
		// JUnitTest
		JSONArray jUnitTestArray = (JSONArray) jsonObject.get("JUnitTest");
		for (int i = 0; i < jUnitTestArray.size(); i++) 
		{
			JSONObject object = (JSONObject) jUnitTestArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int a = 0; a < classAnotation.size(); a++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(a);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");
				String annotationContent = (String) annotation.get("annotationContent");
			}
			List<String> line = new ArrayList();
			JSONArray methods = (JSONArray) object.get("methods");
			for (int b = 0; b < methods.size(); b++) {
				JSONObject objectClass = (JSONObject) methods.get(b);
				JSONObject method = (JSONObject) objectClass.get("method");
				String headerContent = (String) method.get("header");
				JSONArray body = (JSONArray) object.get("body");

				for (int c = 0; c < body.size(); c++) {
					JSONObject lineObject = (JSONObject) body.get(c);
					String LINE = (String) lineObject.get("line");
					line.add(LINE);
				}
			}

		}
	}
	
	
	

	public void writeToJavaFile(String stringData, String fileName) {
		try {

			File file = new File(fileName + ".java");
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
