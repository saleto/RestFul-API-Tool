package pb360.model.embedded;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Line {
	private String line;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Line(@JsonProperty("line") String line) {
		super();
		this.line = line;
	}

	public Line() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
