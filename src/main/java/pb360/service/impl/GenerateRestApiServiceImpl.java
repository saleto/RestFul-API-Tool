package pb360.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		// post.setData("generate restapi files successful");
		// post.setType("POST");
		// post.setDatetime(getCurrentDateTime().getDatetime());
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
			jsonNodeList.add(readCommon(jsonObject));

			jsonDataModel.setTitle(jsonObject.get("title").toString());
			jsonDataModel.setNodes(jsonNodeList);

			writeToFinalFile(jsonNodeList.get(0), "Model");
			writeToFinalFile(jsonNodeList.get(1), "Option");
			writeToFinalFile(jsonNodeList.get(2), "Service");
			writeToFinalFile(jsonNodeList.get(3), "JUnitTest");
			writeToFinalFile(jsonNodeList.get(4), "Controller");
			writeToFinalFile(jsonNodeList.get(5), "Common");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// private void writeModelfile(JsonDataModel jsonDataModel)
	// {
	// List<AnnotationData> listAnnotation = new ArrayList<>();
	// listAnnotation = jsonDataModel.getNodes().get(0).getClassAnotation();
	// List<String> annotaionContentList = new ArrayList<>();
	// StringBuilder result = new StringBuilder();
	// String input = "";
	// input = jsonDataModel.getNodes().get(0).getPackages().toString();
	// result.append(input);
	// result.append("\n");
	// for(int i = 0; i<listAnnotation.size(); i++)
	// {
	// annotaionContentList =
	// jsonDataModel.getNodes().get(0).getClassAnotation().get(i).getAnnotationContent();
	// for(int k = 0; k <annotaionContentList.size();k++)
	// {
	// result.append(annotaionContentList.get(k));
	// }
	// result.append("\n");
	// }
	//
	// input = result.toString();
	//
	// writeToJavaFile(input,"Model");
	// }
	private void writeToFinalFile(JsonNode jsonNode, String name) {
		jsonNode.getPackages();
		jsonNode.getClassAnotation();
		jsonNode.getClassHeader();
		jsonNode.getMethods();

		List<AnnotationData> listAnnotationData = new ArrayList<>();
		List<MethodData> listMethodData = new ArrayList<>();
		listAnnotationData = jsonNode.getClassAnotation();

		String input = "";
		input = jsonNode.getPackages();
		StringBuilder result = new StringBuilder();
		result.append(input);
		result.append("\n");

		for (int i = 0; i < listAnnotationData.size(); i++) {
			if (listAnnotationData.get(i).getAnnotationStarts() != null) {
				result.append(listAnnotationData.get(i).getAnnotationStarts());
				result.append("\n");
			}
			for (int o = 0; o < listAnnotationData.get(i).getAnnotationContent().size(); o++) {
				result.append(listAnnotationData.get(i).getAnnotationContent().get(o));
				result.append("\n");
			}

			if (listAnnotationData.get(i).getAnnotationEnds() != null) {
				result.append(listAnnotationData.get(i).getAnnotationEnds());
			}
		}

		result.append("\n");
		result.append(jsonNode.getClassHeader());
		result.append("\n");
		if (jsonNode.getMethods() != null) {
			listMethodData = jsonNode.getMethods();
			for (int i = 0; i < listMethodData.size(); i++) {
				result.append(listMethodData.get(i).getHeader());
				result.append("\n");
				if (listMethodData.get(i).getBody() != null) {
					for (int k = 0; k < listMethodData.get(i).getBody().size(); k++) {
						result.append(listMethodData.get(i).getBody().get(k));
						result.append("\n");
					}
				}

			}
		}
		input = result.toString();
		writeToJavaFile(input, name);

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

			JSONArray classMethods = (JSONArray) object.get("methods");
			for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
				JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);
				JSONObject method = (JSONObject) objectClass.get("method");

				String headerContent = (String) method.get("header");
				if (headerContent != null && !headerContent.trim().isEmpty()) {
					methodData.setHeader(headerContent);
				}
			}

			listMethodData.add(methodData);
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

			JSONArray classMethods = (JSONArray) object.get("methods");
			for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
				JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);

				JSONArray method = (JSONArray) objectClass.get("method");
				for (int index = 0; index < method.size(); index++) {
					String headerContent = method.get(index).toString();
					if (headerContent != null && !headerContent.trim().isEmpty()) {
						methodData.setHeader(headerContent);
					}
				}

			}

			listMethodData.add(methodData);

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

			JSONArray classMethods = (JSONArray) object.get("methods");
			for (int methodIndex = 0; methodIndex < classMethods.size(); methodIndex++) {
				JSONObject objectClass = (JSONObject) classMethods.get(methodIndex);

				JSONArray method = (JSONArray) objectClass.get("method");
				for (int index = 0; index < method.size(); index++) {
					String headerContent = method.get(index).toString();
					if (headerContent != null && !headerContent.trim().isEmpty()) {
						methodData.setHeader(headerContent);
					}
				}
			}

			listMethodData.add(methodData);

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
						if (headerContent != null && !headerContent.trim().isEmpty()) {
							methodData.setHeader(headerContent);
						}

						JSONArray bodyArray = (JSONArray) tempObject.get("body");
						for (int bodyIndex = 0; bodyIndex < bodyArray.size(); bodyIndex++) {
							String bodyContent = (String) bodyArray.get(bodyIndex).toString();
							if (bodyContent != null && !bodyContent.trim().isEmpty()) {
								bodyList.add(bodyContent);
							}
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

	public void writeToJavaFile(String stringData, String fileName) {
		try {

			File file = new File("D:/restapi/RestFul-API-Tool/javaData", fileName + ".java");
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
