package pb360.model.embedded;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Annotation {// cung lam tuong tu luon, cai nao de lam truoc di e, da, thanks e, nen nho nhung khuc code doc file hoac xu ly co exception ta k ne n hardcode, nhung vay la app cua e chi chay cho e thoi, k ai xai dk, heiu k da em hieu roi, um thanks e!bye
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
