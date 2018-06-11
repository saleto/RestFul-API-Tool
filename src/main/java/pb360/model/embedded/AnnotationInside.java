package pb360.model.embedded;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnotationInside {
	private String annotationStarts;
	private String annotationContent;
	private String annotationEnds;

	public AnnotationInside(@JsonProperty("annotationStarts") String annotationStarts,
			@JsonProperty("annotationContent") String annotationContent,
			@JsonProperty("annotationEnds") String annotationEnds) {

		this.annotationStarts = annotationStarts;
		this.annotationContent = annotationContent;
		this.annotationEnds = annotationEnds;
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

	public AnnotationInside() {
		super();
		// TODO Auto-generated constructor stub
	}

}
