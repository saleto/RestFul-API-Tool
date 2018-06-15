package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonNode {
	private String packages;
	private List<AnnotationInside> classAnotation;
	private String classHeader;
	private List<Methods> Method;
	private String name;

	public JsonNode (@JsonProperty("package") String packages,
			@JsonProperty("classAnotation") List<AnnotationInside> classAnotation,
			@JsonProperty("classHeader") String classHeader,
			@JsonProperty("methods") List<Methods> Method) {
		
		this.packages = packages;
		this.classAnotation = classAnotation;
		this.classHeader = classHeader;
		this.Method = Method;

	}

	public List<Methods> getMethod() {
		return Method;
	}

	public void setMethod(List<Methods> Methods) {
		Method = Methods;
	}

	public JsonNode() {
		super();
	}


	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public List<AnnotationInside> getclassAnotation() {
		return classAnotation;
	}

	public void setclassAnotation(List<AnnotationInside> classAnotation) {
		this.classAnotation = classAnotation;
	}

	public String getClassHeader() {
		return classHeader;
	}

	public void setClassHeader(String classHeader) {
		this.classHeader = classHeader;
	}

}
