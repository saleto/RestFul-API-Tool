package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonNode {

	private String packages;
	private List<AnnotationData> classAnotation;
	private String classHeader;
	private List<MethodData> methods;

	public JsonNode() {
		super();
	}

	public JsonNode(@JsonProperty("package") String packages,
			@JsonProperty("classAnotation") List<AnnotationData> classAnotation,
			@JsonProperty("classHeader") String classHeader, @JsonProperty("methods") List<MethodData> methods) {
		this.packages = packages;
		this.classAnotation = classAnotation;
		this.classHeader = classHeader;
		this.methods = methods;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public List<AnnotationData> getClassAnotation() {
		return classAnotation;
	}

	public void setClassAnotation(List<AnnotationData> classAnotation) {
		this.classAnotation = classAnotation;
	}

	public String getClassHeader() {
		return classHeader;
	}

	public void setClassHeader(String classHeader) {
		this.classHeader = classHeader;
	}

	public List<MethodData> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodData> methods) {
		this.methods = methods;
	}

}
