package pb360.model.embedded;

import java.util.List;

public class method {
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
	public method() {
		super();
		// TODO Auto-generated constructor stub
	}
	public method(String header, String body, List<String> line) {
		super();
		this.header = header;
		this.body = body;
		this.line = line;
	}
	
	

}
