package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonNode {
	private String packages;
	private List<Annotation> classAnnotationList;
	private String classHeader;
	private List<method> Method;

	public JsonNode(@JsonProperty("package") String packages,
			@JsonProperty("classAnotation") List<Annotation> classAnnotationList,
			@JsonProperty("classHeader") String classHeader,
			@JsonProperty("method") List<method> Method) {
		
		this.packages = packages;
		this.classAnnotationList = classAnnotationList;
		this.classHeader = classHeader;
		this.Method = Method;

	}

	public List<method> getMethod() {
		return Method;
	}

	public void setMethod(List<method> method) {
		Method = method;
	}

	public JsonNode() {
		super();
	}

//	public String getNodeName() {
//		return nodeName;
//	}
//
//	public void setNodeName(String nodeName) {
//		this.nodeName = nodeName;
//	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public List<Annotation> getClassAnnotationList() {
		return classAnnotationList;
	}

	public void setClassAnnotationList(List<Annotation> classAnnotationList) {
		this.classAnnotationList = classAnnotationList;
	}

	public String getClassHeader() {
		return classHeader;
	}

	public void setClassHeader(String classHeader) {
		this.classHeader = classHeader;
	}

}
