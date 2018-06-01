package pb360.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class JsonDataModel {
	private @JsonProperty("title") String title;
	private @JsonProperty("Model") JsonNode Model;
	private @JsonProperty("Validator") JsonNode Validator;
	private @JsonProperty("Options") JsonNode Options;
	private @JsonProperty("Service") JsonNode Service;
	private @JsonProperty("Controller") JsonNode Controller;
	private @JsonProperty("JUnitTest") JsonNode JUnitTest;
	private @JsonProperty("Common") JsonNode common;
	
	public JsonDataModel(@JsonProperty("title") String title,
						 @JsonProperty("Model") JsonNode Model,
						 @JsonProperty("Validator") JsonNode Validator,
						 @JsonProperty("Options") JsonNode Options,
						 @JsonProperty("Service") JsonNode Service,
						 @JsonProperty("Controller") JsonNode Controller,
						 @JsonProperty("JUnitTest") JsonNode JUnitTest,
						 @JsonProperty("Common") JsonNode common)
	{
		this.title = title;
		this.Model = Model;
		this.Validator = Validator;
		this.Options = Options;
		this.Service = Service;
		this.Controller = Controller;
		this.JUnitTest = JUnitTest;
		this.common = common;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JsonNode getModel() {
		return Model;
	}

	public void setModel(JsonNode model) {
		Model = model;
	}

	public JsonNode getValidator() {
		return Validator;
	}

	public void setValidator(JsonNode validator) {
		Validator = validator;
	}

	public JsonNode getOptions() {
		return Options;
	}

	public void setOptions(JsonNode options) {
		Options = options;
	}

	public JsonNode getService() {
		return Service;
	}

	public void setService(JsonNode service) {
		Service = service;
	}

	public JsonNode getController() {
		return Controller;
	}

	public void setController(JsonNode controller) {
		Controller = controller;
	}

	public JsonNode getJUnitTest() {
		return JUnitTest;
	}

	public void setJUnitTest(JsonNode jUnitTest) {
		JUnitTest = jUnitTest;
	}

	public JsonNode getCommon() {
		return common;
	}

	public void setCommon(JsonNode common) {
		this.common = common;

	}
}
	
	
	
