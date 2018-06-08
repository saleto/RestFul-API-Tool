package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Annotations {
	private List<AnnotationInside> annotations;
	private String annotation;
	
	public Annotations()
	{
		super();
	}
	public Annotations( @JsonProperty("annotation")  List<AnnotationInside>  annotations)
	{
		this.annotations = annotations;
	}
	
	public Annotations(@JsonProperty("annotation") String annotation)
	{
		this.annotation = annotation;
	}

	
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public List<AnnotationInside> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<AnnotationInside> annotations) {
		this.annotations = annotations;
	}
	
}
