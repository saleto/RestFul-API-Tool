package pb360.model.embedded;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Methods {
	private List<MethodInside> methods;

	public Methods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<MethodInside> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodInside> methods) {
		this.methods = methods;
	}

	public Methods(@JsonProperty("method") List<MethodInside> methods) {
		super();
		this.methods = methods;
	}
	
	

}
