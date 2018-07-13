package pb360.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import pb360.model.embedded.JsonNode;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class JsonDataModel extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private List<JsonNode> nodes;

	public JsonDataModel() {
		super();
	}

	public JsonDataModel(@JsonProperty("title") String title, @JsonProperty("nodes") List<JsonNode> nodes) {
		this.title = title;
		this.nodes = nodes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<JsonNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<JsonNode> nodes) {
		this.nodes = nodes;
	}

}