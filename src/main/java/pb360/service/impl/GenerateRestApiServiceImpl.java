package pb360.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import pb360.model.embedded.MethodData;
import pb360.service.GenerateRestApiService;

@Service
public final class GenerateRestApiServiceImpl implements GenerateRestApiService {

	private static final String json_link = "D:\\restapi\\JSON_Sample.json";
	private static final String SEPARATOR_BLANK = "";
	private static final String userInputName ="defaultFunc";
	private static int _id = 0;
	private static String defaultLink = "D:/restapi/RestFul-API-Tool/javaData";

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
		List<String> fileOfRest = new ArrayList<>();
		
		restApi.setRestId(_id+"");
		_id = _id ++;
		MessageObject post = new MessageObject();
		post.setData("generate restapi files successful");
		post.setType("POST");
		post.setDatetime(getCurrentDateTime().getDatetime());
		String filename = restApi.getRestName();
		String directory = restApi.getRestUrl();
		
		

		readJson(json_link, filename, directory);
		fileOfRest.add(filename + "Model");
		fileOfRest.add(filename + "Option");
		fileOfRest.add(filename + "Service");
		fileOfRest.add(filename + "JUnitTest");
		fileOfRest.add(filename + "Controller");
		fileOfRest.add(filename + "Common");
		
		restApi.setFileOfRest(fileOfRest);
		restApi.setRestName(filename);
		restApi.getRestUrl();
		restApi.setRestLocation("pb360.test.");
		
		post.setData("Complete");
		post.setType("Generate completed");
		restApi.setRestStatus(post);
		
	    
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

	private void readJson(String link, String filename, String directory) {
		JsonDataModel jsonDataModel = new JsonDataModel();
		List<JsonNode> jsonNodeList = new ArrayList<JsonNode>();

		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(link));
			JSONObject jsonObject = (JSONObject) obj;

			jsonNodeList.add(readModel(jsonObject));
			jsonNodeList.add(readValidator(jsonObject));
			jsonNodeList.add(readOption(jsonObject));
			jsonNodeList.add(readService(jsonObject));
			jsonNodeList.add(readJUnitTest(jsonObject));
			jsonNodeList.add(readController(jsonObject));
			jsonNodeList.add(readCommon(jsonObject));

			jsonDataModel.setTitle(jsonObject.get("title").toString());
			jsonDataModel.setNodes(jsonNodeList);
			
			

			writeToFinalFile(jsonNodeList.get(0), filename+"Model", filename+"Model", directory, 0);
			writeToFinalFile(jsonNodeList.get(1), filename+"Validator", filename+"Validator",directory, 1);
			writeToFinalFile(jsonNodeList.get(2), filename+"Option", filename+"Option", directory, 2);
			writeToFinalFile(jsonNodeList.get(3), filename+"Service", filename+"Service", directory, 3);
			writeToFinalFile(jsonNodeList.get(4), filename+"JUnitTest", filename+"JUnitTest", directory, 4);
			writeToFinalFile(jsonNodeList.get(5), filename+"Controller", filename+"Controller", directory, 5);
			writeToFinalFile(jsonNodeList.get(6), filename+"Common", filename+"Common", directory, 6);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void writeToFinalFile(JsonNode jsonNode, String name, String controllerName, String directory, int fileIndex) {
	
		
//		jsonNode.getClassAnotation();
//		jsonNode.getClassHeader();
//		jsonNode.getMethods();

		List<AnnotationData> listAnnotationData = new ArrayList<>();
		List<MethodData> listMethodData = new ArrayList<>();
		
		listAnnotationData = jsonNode.getClassAnotation();
		

		String input = "";
		StringBuilder result = new StringBuilder();
		if(jsonNode.getPackages()!= null)
		{
			input = jsonNode.getPackages();
			
			result.append(input);
			result.append(";\n");
		}
		
		result.append("import java.util.*;\n");
		result.append("import org.eclipse.jetty.http.HttpStatus;\n");
		result.append("import org.springframework.hateoas.Link;\n");
		result.append("import org.springframework.hateoas.Resource;\n");
		result.append("import org.springframework.http.ResponseEntity;\n");
		result.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		result.append("import org.springframework.web.bind.annotation.RestController;\n");
		result.append("import org.springframework.ui.Model;\n");
		result.append("import pb360.*;");
		
		
		
		
		
		

		for (int i = 0; i < listAnnotationData.size(); i++) {
			if (listAnnotationData.get(i).getAnnotationStarts() != null) {
				result.append(listAnnotationData.get(i).getAnnotationStarts());
				result.append("\n");
			}
			
			for (int o = 0; o < listAnnotationData.get(i).getAnnotationContent().size(); o++) {
				
				if(listAnnotationData.get(i).getAnnotationContent().get(o) == null)
				{
					result.append("");
					
				}
				else
				{
					input = listAnnotationData.get(i).getAnnotationContent().get(o);
					input = input.replace("{1}", "\""+directory+"\"");
					result.append(input);				
					result.append("\n");
				}
				
			}

			if (listAnnotationData.get(i).getAnnotationEnds() != null) {
				result.append(listAnnotationData.get(i).getAnnotationEnds());
			}
		
			
		}

		
		result.append("\n");
		if(jsonNode.getClassHeader()!= null)
		{
			input = jsonNode.getClassHeader();
			input = input.replace("{1}", controllerName);
			result.append(input);
		
			result.append("{\n");
		}
		
		if (jsonNode.getMethods() != null) {
			listMethodData = jsonNode.getMethods();
			for (int i = 0; i < listMethodData.size(); i++) {
				if (listMethodData.get(i).getHeader() != null)
				{
				result.append(listMethodData.get(i).getHeader());
				result.append("\n");
				}
				if (listMethodData.get(i).getBody() != null) {
					for (int k = 0; k < listMethodData.get(i).getBody().size(); k++) {
						result.append(listMethodData.get(i).getBody().get(k));
						result.append("\n");
					}
				}

			}
		}
		else
		{
			result.append("");
		}
		
		
		result.append("\n}");
		input = result.toString();
	
		
//		input = input.replace("ControllerName", controllerName);
//		if(fileIndex == 0)
//		{
//			writeToJavaFile(input, name, "D:\\restapi\\nextgen-sti\\src\\main\\java\\pb360\\model");
//		}
//		else if(fileIndex == 1)
//		{
//			writeToJavaFile(input, name, "D:\\restapi\\nextgen-sti\\src\\main\\java\\pb360\\option");
//		}
//		else if(fileIndex == 2)
//		{
//			writeToJavaFile(input, name, "D:\\restapi\\nextgen-sti\\src\\main\\java\\pb360\\service");
//		}
//		else if(fileIndex == 3)
//		{
//			writeToJavaFile(input, name, "");
//		}
//		else if(fileIndex ==4)
//		{
//			writeToJavaFile(input, name, "");
//		}
//		else if(fileIndex == 5)
//		{
//			writeToJavaFile(input, name, "");
//		}
//		else
//		{
//			System.out.println("something WRONG");
//		}
		
		writeToJavaFile(input, name, defaultLink);

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
		AnnotationData annotationData = new AnnotationData();
		List<String> annotationContentList = new ArrayList<>();
		MethodData methodData = new MethodData();
		List<MethodData> listMethodData = new ArrayList<>();
		List<String> bodyList = new ArrayList<>();
		JSONArray validatorArray = (JSONArray) jsonObject.get("Validator");
		for (int i = 0; i < validatorArray.size(); i++) {
			JSONObject object = (JSONObject) validatorArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);

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

			if (object.get("methods") != null) {
				JSONArray classMethods = (JSONArray) object.get("methods");
				for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
					JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);

					JSONArray method = (JSONArray) objectClass.get("method");

					for (int index = 0; index < method.size(); index++) {
						JSONObject tempObject = (JSONObject) method.get(index);
						String headerContent = (String) tempObject.get("header");
					
//							methodData.setHeader(headerContent);
							bodyList.add(headerContent + "{" );


						if(tempObject.get("body") != null)
						{
						JSONArray bodyArray = (JSONArray) tempObject.get("body");
						for (int bodyIndex = 0; bodyIndex < bodyArray.size(); bodyIndex++) {
							JSONObject bodyContent = (JSONObject) bodyArray.get(bodyIndex);

							String singeLine = (String) bodyContent.get("line");

							bodyList.add(singeLine+";");

						}
						}
					
					}
					if(methodIndex < classMethods.size())
					{
						bodyList.add("}");
					}
					methodData.setBody(bodyList);
				}
//				methodData.setBody(bodyList);
				listMethodData.add(methodData);

			}
		}

		jsonNode.setClassAnotation(listAnnotationData);
		jsonNode.setMethods(listMethodData);
		return jsonNode;
	}

	public JsonNode readOption(JSONObject jsonObject) {
		// Options
		JsonNode jsonNode = new JsonNode();
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		MethodData methodData = new MethodData();
		List<MethodData> listMethodData = new ArrayList<>();
	
		List<String> bodyList = new ArrayList<>();

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

			if (object.get("methods") != null) {
				JSONArray classMethods = (JSONArray) object.get("methods");
				for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
					JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);

					JSONArray method = (JSONArray) objectClass.get("method");

					for (int index = 0; index < method.size(); index++) {
						JSONObject tempObject = (JSONObject) method.get(index);
						String headerContent = (String) tempObject.get("header");
					
//							methodData.setHeader(headerContent);
							bodyList.add(headerContent + "{" );


						if(tempObject.get("body") != null)
						{
						JSONArray bodyArray = (JSONArray) tempObject.get("body");
						for (int bodyIndex = 0; bodyIndex < bodyArray.size(); bodyIndex++) {
							JSONObject bodyContent = (JSONObject) bodyArray.get(bodyIndex);

							String singeLine = (String) bodyContent.get("line");

							bodyList.add(singeLine+";");

						}
						}
					
					}
					if(methodIndex < classMethods.size())
					{
						bodyList.add("}");
					}
					methodData.setBody(bodyList);
				}
//				methodData.setBody(bodyList);
				listMethodData.add(methodData);

		}
		}
		jsonNode.setClassAnotation(listAnnotationData);
		jsonNode.setMethods(listMethodData);
		return jsonNode;
	}

	public JsonNode readService(JSONObject jsonObject) {
		// Service
		JsonNode jsonNode = new JsonNode();
		MethodData methodData = new MethodData();
		List<MethodData> listMethodData = new ArrayList<>();
		
		List<String> bodyList = new ArrayList<>();

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
			if (object.get("classAnotation") != null) {
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

			if (object.get("methods") != null) {
				JSONArray classMethods = (JSONArray) object.get("methods");
				for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
					JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);

					JSONArray method = (JSONArray) objectClass.get("method");

					for (int index = 0; index < method.size(); index++) {
						JSONObject tempObject = (JSONObject) method.get(index);
						String headerContent = (String) tempObject.get("header");
					
//							methodData.setHeader(headerContent);
							bodyList.add(headerContent + "{" );


							
						if(tempObject.get("body")!= null)
						{
						JSONArray bodyArray = (JSONArray) tempObject.get("body");
						for (int bodyIndex = 0; bodyIndex < bodyArray.size(); bodyIndex++) {
							JSONObject bodyContent = (JSONObject) bodyArray.get(bodyIndex);

							String singeLine = (String) bodyContent.get("line");

							bodyList.add(singeLine+";");

						}
						}
					
					}
					if(methodIndex < classMethods.size())
					{
						bodyList.add("}");
					}
					methodData.setBody(bodyList);
				}
//				methodData.setBody(bodyList);
				listMethodData.add(methodData);

			}
		}

		jsonNode.setClassAnotation(listAnnotationData);
		jsonNode.setMethods(listMethodData);

		return jsonNode;
	}

	public JsonNode readController(JSONObject jsonObject) {
		// Controller
		JsonNode jsonNode = new JsonNode();
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		MethodData methodData = new MethodData();
		List<MethodData> listMethodData = new ArrayList<>();
		List<String> bodyList = new ArrayList<>();

		AnnotationData annotationData = new AnnotationData();
		List<String> annotationContentList = new ArrayList<>();

		JSONArray controllerArray = (JSONArray) jsonObject.get("Controller");
		for (int i = 0; i < controllerArray.size(); i++) {
			JSONObject object = (JSONObject) controllerArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);

				if (objectClass.get("annotation") instanceof String) {
					annotationContentList.add(objectClass.get("annotation").toString());
				} else {

					JSONObject annotation = (JSONObject) objectClass.get("annotation");

					if (annotation.get("annotationContent") instanceof String) {
						String annotationContent = (String) annotation.get("annotationContent");
						if (annotationContent != null && !annotationContent.trim().isEmpty()) {
							annotationContentList.add(annotationContent);
						}
					} else {

						JSONArray annotationContent = (JSONArray) annotation.get("annotationContent");
						
						for (int Index = 0; Index < annotationContent.size(); Index++) {
							JSONObject objClass = (JSONObject) annotationContent.get(Index);
					
							String a = objClass.get("annotationParam").toString();

							annotationContentList.add(a);

						}
					}

				}
			}
			annotationData.setAnnotationStarts(SEPARATOR_BLANK);
			annotationData.setAnnotationContent(annotationContentList);
			annotationData.setAnnotationEnds(SEPARATOR_BLANK);

			listAnnotationData.add(annotationData);

			if (object.get("methods") != null) {
				JSONArray classMethods = (JSONArray) object.get("methods");
				for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
					JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);

					JSONArray method = (JSONArray) objectClass.get("method");

					for (int index = 0; index < method.size(); index++) {
						JSONObject tempObject = (JSONObject) method.get(index);
						String headerContent = (String) tempObject.get("header");
					
//							methodData.setHeader(headerContent);
							bodyList.add(headerContent + "{" );


						if(tempObject.get("body") != null)
						{
						JSONArray bodyArray = (JSONArray) tempObject.get("body");
						for (int bodyIndex = 0; bodyIndex < bodyArray.size(); bodyIndex++) {
							JSONObject bodyContent = (JSONObject) bodyArray.get(bodyIndex);

							String singeLine = (String) bodyContent.get("line");

							bodyList.add(singeLine+";");

						}
						}
					
					}
					if(methodIndex < classMethods.size())
					{
						bodyList.add("}");
					}
					methodData.setBody(bodyList);
				}
//				methodData.setBody(bodyList);
				listMethodData.add(methodData);

			}
		}

		jsonNode.setClassAnotation(listAnnotationData);
		jsonNode.setMethods(listMethodData);

		return jsonNode;
	}

	public JsonNode readJUnitTest(JSONObject jsonObject) {
		// JUnitTest
		MethodData methodData = new MethodData();
		List<MethodData> listMethodData = new ArrayList<>();
		List<String> bodyList = new ArrayList<>();
		AnnotationData annotationData = new AnnotationData();
		List<String> annotationContentList = new ArrayList<>();

		JsonNode jsonNode = new JsonNode();
		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		JSONArray jUnitTestArray = (JSONArray) jsonObject.get("JUnitTest");
		for (int i = 0; i < jUnitTestArray.size(); i++) {
			JSONObject object = (JSONObject) jUnitTestArray.get(i);
			String packageName = (String) object.get("package");
			String classHeader = (String) object.get("classHeader");
			jsonNode.setPackages(packageName);
			jsonNode.setClassHeader(classHeader);

			// loop classAnotationArray
			JSONArray classAnotation = (JSONArray) object.get("classAnotation");
			for (int annoIndex = 0; annoIndex < classAnotation.size(); annoIndex++) {
				JSONObject objectClass = (JSONObject) classAnotation.get(annoIndex);
				JSONObject annotation = (JSONObject) objectClass.get("annotation");

				if (annotation.get("annotationContent") instanceof String) {
					String annotationContent = (String) annotation.get("annotationContent");
					if (annotationContent != null && !annotationContent.trim().isEmpty()) {
						annotationContentList.add(annotationContent);
					}
				} else {

					JSONArray annotationContent = (JSONArray) annotation.get("annotationContent");
					for (int Index = 0; Index < annotationContent.size(); Index++) {
						JSONObject objClass = (JSONObject) annotationContent.get(Index);
						String a = objClass.get("annotationParam").toString();

						annotationContentList.add(a);

					}
				}

				String annotationStart = (String) annotation.get("annotationStarts");
				if (annotationStart != null && !annotationStart.trim().isEmpty()) {
					annotationData.setAnnotationStarts(annotationStart);
				}

				String annotationEnd = (String) annotation.get("annotationEnds");
				if (annotationEnd != null && !annotationEnd.trim().isEmpty()) {
					annotationData.setAnnotationStarts(annotationEnd);
				}
			}

			annotationData.setAnnotationContent(annotationContentList);

			listAnnotationData.add(annotationData);

			if (object.get("methods") != null) {
				JSONArray classMethods = (JSONArray) object.get("methods");
				for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
					JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);
					JSONObject method = (JSONObject) objectClass.get("method");

					String headerContent = (String) method.get("header");
					if (headerContent != null && !headerContent.trim().isEmpty()) {
						methodData.setHeader(headerContent);
					}

					JSONArray bodyArray = (JSONArray) method.get("body");
					for (int bodyIndex = 0; bodyIndex < bodyArray.size(); bodyIndex++) {
						String bodyContent = (String) method.get("line");
						if (bodyContent != null && !bodyContent.trim().isEmpty()) {
							bodyList.add(bodyContent);
						}
					}
				}
				methodData.setBody(bodyList);
				listMethodData.add(methodData);

			}

		}
		jsonNode.setClassAnotation(listAnnotationData);
		jsonNode.setMethods(listMethodData);
		return jsonNode;
	}

	public JsonNode readCommon(JSONObject jsonObject) {
		JsonNode jsonNode = new JsonNode();
		AnnotationData annotationData = new AnnotationData();
		List<String> annotationContentList = new ArrayList<>();

		List<AnnotationData> listAnnotationData = new ArrayList<AnnotationData>();
		JSONArray modelArray = (JSONArray) jsonObject.get("Common");
		for (int index = 0; index < modelArray.size(); index++) {
			JSONObject object = (JSONObject) modelArray.get(index);

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

	public void writeToJavaFile(String stringData, String fileName, String link) {
		try {
			String defaultLink = "D:/restapi/RestFul-API-Tool/javaData";

			File file = new File(link, fileName + ".java");
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
