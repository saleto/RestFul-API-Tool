package pb360.model.embedded;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Annotations {
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<AnnotationInside> annotations;
	
	public Annotations()
	{
		super();
	}
	public Annotations( @JsonProperty("annotation")  List<AnnotationInside>  annotations)
	{
		
			this.annotations=annotations;
	}
	

	

	public List<AnnotationInside> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<AnnotationInside> annotations) {
		this.annotations = annotations;
	}
	
}
