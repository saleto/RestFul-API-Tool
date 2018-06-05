package pb360.model.embedded;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Annotation {
	private String annotationStarts;
	private String annotationContent;
	private String annotationEnds;
	
	public Annotation()
	{
		super();
	}
	public Annotation( @JsonProperty("annotationContent")  String annotationContent)
	{
//		this.annotationStarts = annotationStarts;
		this.annotationContent = annotationContent;
//		this.annotationEnds = annotationEnds;
	}
	public String getAnnotationStarts() {
		return annotationStarts;
	}
	public void setAnnotationStarts(String annotationStarts) {
		this.annotationStarts = annotationStarts;
	}
	public String getAnnotationContent() {
		return annotationContent;
	}
	public void setAnnotationContent(String annotationContent) {
		this.annotationContent = annotationContent;
	}
	public String getAnnotationEnds() {
		return annotationEnds;
	}
	public void setAnnotationEnds(String annotationEnds) {
		this.annotationEnds = annotationEnds;
	}

}
