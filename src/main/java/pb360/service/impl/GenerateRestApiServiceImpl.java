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
import pb360.model.embedded.AnnotationData;
import pb360.model.embedded.JsonNode;
import pb360.service.GenerateRestApiService;

@Service
public final class GenerateRestApiServiceImpl implements GenerateRestApiService {

	private static final String json_link = "D:\\restapi\\JSON_Sample.json";
	private static final String SEPARATOR_BLANK = "";

	@Override
	public MessageObject getRestApiData(String restId) {
		MessageObject getMessage = new MessageObject();
		getMessage.setData("Get RestApi data successful: " + restId);
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
		readJson(json_link);
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
		JsonDataModel jsonDataModel = new JsonDataModel();
		List<JsonNode> jsonNodeList = new ArrayList<JsonNode>();

		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(link));
			JSONObject jsonObject = (JSONObject) obj;

			jsonNodeList.add(readModel(jsonObject));
			jsonNodeList.add(readOption(jsonObject));
			jsonNodeList.add(readService(jsonObject));
			jsonNodeList.add(readJUnitTest(jsonObject));
			jsonNodeList.add(readController(jsonObject));

			jsonDataModel.setTitle(jsonObject.get("title").toString());
			jsonDataModel.setNodes(jsonNodeList);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public JsonNode readModel(JSONObject jsonObject) {
		JsonNode jsonNode = new JsonNode();
		
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		JSONArray modelArray = (JSONArray) jsonObject.get("Model");
		for (int index = 0; index < modelArray.size(); index++) {
			JSONObject object = (JSONObject) modelArray.get(index);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			
			
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);

			AnnotationData annotationData = new AnnotationData();
			List<String> annotationContentList = new ArrayList<>();
			
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");

				String annotationContent = (String) annotation.get("annotationContent");
				if (annotationContent != null && !annotationContent.trim().isEmpty()) {
					annotationContentList.add(annotationContent);
				}
			}

			annotationData.setAnnotationStarts(SEPARATOR_BLANK);
			annotationData.setAnnotationContent(annotationContentList);
			annotationData.setAnnotationEnds(SEPARATOR_BLANK);
			
			listAnnotationData.add(annotationData);
		}
		jsonNode.setClassAnotation(listAnnotationData);
		return jsonNode;
	}

	public JsonNode readValidator(JSONObject jsonObject) {
		// Validator
		JsonNode jsonNode = new JsonNode();
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		JSONArray validatorArray = (JSONArray) jsonObject.get("Validator");
		for (int i = 0; i < validatorArray.size(); i++) {
			JSONObject object = (JSONObject) validatorArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);
			
			AnnotationData annotationData = new AnnotationData();
			List<String> annotationContentList = new ArrayList<>();

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");

				String annotationContent = (String) annotation.get("annotationContent");
				if (annotationContent != null && !annotationContent.trim().isEmpty()) {
					annotationContentList.add(annotationContent);
				}
			}
			annotationData.setAnnotationStarts(SEPARATOR_BLANK);
			annotationData.setAnnotationContent(annotationContentList);
			annotationData.setAnnotationEnds(SEPARATOR_BLANK);
			
			listAnnotationData.add(annotationData);

		}
		jsonNode.setClassAnotation(listAnnotationData);
		return jsonNode;
	}

	public JsonNode readOption(JSONObject jsonObject) {
		// Options
		JsonNode jsonNode = new JsonNode();
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		JSONArray optionsArray = (JSONArray) jsonObject.get("Options");
		for (int i = 0; i < optionsArray.size(); i++) {
			JSONObject object = (JSONObject) optionsArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);
			
			AnnotationData annotationData = new AnnotationData();
			List<String> annotationContentList = new ArrayList<>();

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");

				String annotationContent = (String) annotation.get("annotationContent");
				if (annotationContent != null && !annotationContent.trim().isEmpty()) {
					annotationContentList.add(annotationContent);
				}
			}
			annotationData.setAnnotationStarts(SEPARATOR_BLANK);
			annotationData.setAnnotationContent(annotationContentList);
			annotationData.setAnnotationEnds(SEPARATOR_BLANK);
			
			listAnnotationData.add(annotationData);

		}
		jsonNode.setClassAnotation(listAnnotationData);
		return jsonNode;
	}

	public JsonNode readService(JSONObject jsonObject) {
		// Service
		JsonNode jsonNode = new JsonNode();
		
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		JSONArray serviceArray = (JSONArray) jsonObject.get("Service");
		for (int i = 0; i < serviceArray.size(); i++) {
			JSONObject object = (JSONObject) serviceArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);
			
			AnnotationData annotationData = new AnnotationData();
			List<String> annotationContentList = new ArrayList<>();

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");

				String annotationContent = (String) annotation.get("annotationContent");
				if (annotationContent != null && !annotationContent.trim().isEmpty()) {
					annotationContentList.add(annotationContent);
				}
			}
			
			annotationData.setAnnotationStarts(SEPARATOR_BLANK);
			annotationData.setAnnotationContent(annotationContentList);
			annotationData.setAnnotationEnds(SEPARATOR_BLANK);
			
			listAnnotationData.add(annotationData);

			JSONArray methods = (JSONArray) object.get("methods");
			for (int b = 0; b < methods.size(); b++) {
				JSONObject objectClass = (JSONObject) methods.get(b);
				JSONObject method = (JSONObject) objectClass.get("method");
				String headerContent = (String) method.get("header");
			}

		}
		jsonNode.setClassAnotation(listAnnotationData);
		return jsonNode;
	}

	public JsonNode readController(JSONObject jsonObject) {
		// Controller
		JsonNode jsonNode = new JsonNode();
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();

		JSONArray controllerArray = (JSONArray) jsonObject.get("Controller");
		for (int i = 0; i < controllerArray.size(); i++) {
			JSONObject object = (JSONObject) controllerArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);
			
			AnnotationData annotationData = new AnnotationData();
			List<String> annotationContentList = new ArrayList<>();


			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");

				String annotationContent = (String) annotation.get("annotationContent");
				if (annotationContent != null && !annotationContent.trim().isEmpty()) {
					annotationContentList.add(annotationContent);
				}
			}
			
			annotationData.setAnnotationStarts(SEPARATOR_BLANK);
			annotationData.setAnnotationContent(annotationContentList);
			annotationData.setAnnotationEnds(SEPARATOR_BLANK);
			
			listAnnotationData.add(annotationData);
		}
		
		jsonNode.setClassAnotation(listAnnotationData);
		return jsonNode;
	}

	public JsonNode readJUnitTest(JSONObject jsonObject) {
		// JUnitTest
		JsonNode jsonNode = new JsonNode();
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		JSONArray jUnitTestArray = (JSONArray) jsonObject.get("JUnitTest");
		for (int i = 0; i < jUnitTestArray.size(); i++) {
			JSONObject object = (JSONObject) jUnitTestArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);
			
			AnnotationData annotationData = new AnnotationData();
			List<String> annotationContentList = new ArrayList<>();

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");

				String annotationContent = (String) annotation.get("annotationContent");
				if (annotationContent != null && !annotationContent.trim().isEmpty()) {
					annotationContentList.add(annotationContent);
				}
			}
			
			annotationData.setAnnotationStarts(SEPARATOR_BLANK);
			annotationData.setAnnotationContent(annotationContentList);
			annotationData.setAnnotationEnds(SEPARATOR_BLANK);
			
			listAnnotationData.add(annotationData);
			
			
//			List<String> line = new ArrayList();
//			JSONArray methods = (JSONArray) object.get("methods");
//			for (int b = 0; b < methods.size(); b++) {
//				JSONObject objectClass = (JSONObject) methods.get(b);
//				JSONObject method = (JSONObject) objectClass.get("method");
//				String headerContent = (String) method.get("header");
//				JSONArray body = (JSONArray) object.get("body");
//
//				for (int c = 0; c < body.size(); c++) {
//					JSONObject lineObject = (JSONObject) body.get(c);
//					String LINE = (String) lineObject.get("line");
//					line.add(LINE);
//				}
//			}

		}
		return jsonNode;
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
