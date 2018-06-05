package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonNode {// sua lai cho dung cau truc, tham khao jsondatamodel nha e, da
	private String nodeName;
	private String packages;
	private List<Annotation> classAnnotationList;
	private String classHeader;

	public JsonNode(@JsonProperty("nodeName") String nodeName, @JsonProperty("package") String packages,
			@JsonProperty("classAnnotationList") List<Annotation> classAnnotationList,
			@JsonProperty("classHeader") String classHeader) {
		this.nodeName = nodeName;
		this.packages = packages;
		this.classAnnotationList = classAnnotationList;
		this.classHeader = classHeader;

	}
	
	public JsonNode()
	{
		super();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

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
