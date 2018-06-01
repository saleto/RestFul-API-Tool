package pb360.model.embedded;


import java.util.List;

public class JsonNewNode {
	private String nodeName;
	private String packages;
	private List<Annotation> classAnnotationList;
	private String classHeader;
	public JsonNewNode()
	{
		
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
