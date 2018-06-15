package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MethodInside {
	private String header;
	private List<String> body;
//	private List<String> String;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public List<String> getBody() {
		return body;
	}
	public void setBody(List<String> body) {
		this.body = body;
	}

	public MethodInside() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MethodInside(@JsonProperty("header") String header,@JsonProperty("body") List<String> body) {
		this.header = header;
		this.body = body;
	}
	
	

}
