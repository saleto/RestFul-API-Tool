package pb360.model.embedded;

public class Annotation {
	private String annotationStarts;
	private String annotationContent;
	private String annotationEnds;
	
	public Annotation()
	{
		
	}
	public Annotation(String annotationStarts, String annotationContent, String annotationEnds)
	{
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

}
