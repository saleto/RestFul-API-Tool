package pb360.model.embedded;

import java.util.List;

public class methods {
	private String hearder;
	private List<line> body;
	public methods()
	{
		
	}
	
	public methods(String header, List<line> body)
	{
		this.hearder = header;
		this.body = body;
	}
	public String getHearder() {
		return hearder;
	}
	public void setHearder(String hearder) {
		this.hearder = hearder;
	}
	public List<line> getBody() {
		return body;
	}
	public void setBody(List<line> body) {
		this.body = body;
	}
	
	

}
