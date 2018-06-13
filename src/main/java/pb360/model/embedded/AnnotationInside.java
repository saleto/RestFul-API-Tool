package pb360.model.embedded;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnotationInside {
	private String annotationStarts;	
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> annotationContent;
	private String annotationEnds;
	private String annotations;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public AnnotationInside(@JsonProperty("annotationStarts") String annotationStarts,
			@JsonProperty("annotationContent") List<String> annotationContent,
			@JsonProperty("annotationEnds") String annotationEnds,
			@JsonProperty("annotation") String annotations) {

			this.annotations=annotations;


		this.annotationStarts = annotationStarts;
		this.annotationContent = annotationContent;
		this.annotationEnds = annotationEnds;

	
	}
	
	
	
	public String getAnnotations() {
		return annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
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

	public AnnotationInside() {
		super();
		// TODO Auto-generated constructor stub
	}


}
