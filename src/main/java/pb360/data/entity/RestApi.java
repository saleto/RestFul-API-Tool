package pb360.data.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import pb360.model.MessageObject;

@Document(collection= "RestApi")

public final class RestApi {
	private @JsonProperty("restId") String restId;

	private @JsonProperty("restName") String restName;

	private @JsonProperty("restUrl") String restUrl;

	private @JsonProperty("restStatus") MessageObject restStatus;

	private @JsonProperty("fileOfRest") List<String> fileOfRest;

	private @JsonProperty("restLocation") String restLocation;

	private @JsonProperty("lastModifying") Date lastModifying;

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public MessageObject getRestStatus() {
		return restStatus;
	}

	public void setRestStatus(MessageObject restStatus) {
		this.restStatus = restStatus;
	}

	public List<String> getFileOfRest() {
		return fileOfRest;
	}

	public void setFileOfRest(List<String> fileOfRest) {
		this.fileOfRest = fileOfRest;
	}

	public String getRestLocation() {
		return restLocation;
	}

	public void setRestLocation(String restLocation) {
		this.restLocation = restLocation;
	}

	public Date getLastModifying() {
		return lastModifying;
	}

	public void setLastModifying(Date lastModifying) {
		this.lastModifying = lastModifying;
	}

}
