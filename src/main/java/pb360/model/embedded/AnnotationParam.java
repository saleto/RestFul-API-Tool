package pb360.model.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnotationParam {
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private String annotationParam;

	public String getAnnotationParam() {
		return annotationParam;
	}

	public void setAnnotationParam(String annotationParam) {
		this.annotationParam = annotationParam;
	}

	public AnnotationParam(@JsonProperty("annotationParam")String annotationParam) {
		super();
		this.annotationParam = annotationParam;
	}

	public AnnotationParam() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
