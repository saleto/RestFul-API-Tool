package pb360.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pb360.model.embedded.JsonNode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class JsonDataModel {
	private String title;
	private List<JsonNode> Model;
	private List<JsonNode> Validator;
	private List<JsonNode> Options;
	private List<JsonNode> Service;
	private List<JsonNode> Controller;
	private List<JsonNode> JUnitTest;
	private List<JsonNode> Common;

	public JsonDataModel() {
		super();
	}
	
	public JsonDataModel(@JsonProperty("title") String title, @JsonProperty("Model") List<JsonNode> Model,
			@JsonProperty("Validator") List<JsonNode> Validator,
			@JsonProperty("Options") List<JsonNode> Options,
			@JsonProperty("Service") List<JsonNode> Service,
			@JsonProperty("Controller") List<JsonNode> Controller,
			@JsonProperty("JUnitTest") List<JsonNode> JUnitTest,
			@JsonProperty("Common") List<JsonNode> Common) {
		this.title = title;
		this.Model = Model;
		this.Validator = Validator;
		this.Options = Options;
		this.Service = Service;
		this.Controller = Controller;
		this.JUnitTest = JUnitTest;
		this.Common = Common;
	}
	
	
	
	
	public List<JsonNode> getValidator() {
		return Validator;
	}


	public void setValidator(List<JsonNode> validator) {
		Validator = validator;
	}


	public List<JsonNode> getOptions() {
		return Options;
	}


	public void setOptions(List<JsonNode> options) {
		Options = options;
	}


	public List<JsonNode> getService() {
		return Service;
	}


	public void setService(List<JsonNode> service) {
		Service = service;
	}


	public List<JsonNode> getController() {
		return Controller;
	}


	public void setController(List<JsonNode> controller) {
		Controller = controller;
	}


	public List<JsonNode> getJUnitTest() {
		return JUnitTest;
	}


	public void setJUnitTest(List<JsonNode> jUnitTest) {
		JUnitTest = jUnitTest;
	}


	public List<JsonNode> getCommon() {
		return Common;
	}


	public void setCommon(List<JsonNode> common) {
		Common = common;
	}


	



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<JsonNode> getModel() {
		return Model;
	}

	public void setModel(List<JsonNode> Model) {
		this.Model = Model;
	}
}
