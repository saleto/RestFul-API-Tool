package pb360.model.embedded;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnotationData {

	private String annotationStarts;
	private List<String> annotationContent;
	private String annotationEnds;

	public AnnotationData() {
		super();
	}

	public AnnotationData(@JsonProperty("annotationStarts") String annotationStarts,
			@JsonProperty("annotationContent") List<String> annotationContent,
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

	public List<String> getAnnotationContent() {
		return annotationContent;
	}

	public void setAnnotationContent(List<String> annotationContent) {
		this.annotationContent = annotationContent;
	}

	public String getAnnotationEnds() {
		return annotationEnds;
	}

	public void setAnnotationEnds(String annotationEnds) {
		this.annotationEnds = annotationEnds;
	}

}
