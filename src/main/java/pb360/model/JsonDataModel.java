package pb360.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pb360.model.embedded.JsonNode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class JsonDataModel {
	private String title;
	private List<JsonNode> nodeList;

	public JsonDataModel() {
		super();
	}
	

	public JsonDataModel(@JsonProperty("title") String title, @JsonProperty("nodeList") List<JsonNode> nodeList) {
		this.title = title;
		this.nodeList = nodeList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<JsonNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<JsonNode> nodeList) {
		this.nodeList = nodeList;
	}
}
