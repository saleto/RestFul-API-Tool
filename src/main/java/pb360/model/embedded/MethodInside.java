package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MethodInside {
	private String header;
	private String body;
	private List<String> line;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getLine() {
		return line;
	}
	public void setLine(List<String> line) {
		this.line = line;
	}
	public MethodInside() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MethodInside(@JsonProperty("header") String header,@JsonProperty("body") String body,@JsonProperty("line") List<String> line) {
		this.header = header;
		this.body = body;
		this.line = line;
	}
	
	

}
