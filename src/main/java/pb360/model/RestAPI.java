package pb360.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_DEFAULT)
public class RestAPI extends ResourceSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String restId;

	private String restName;

	private String restUrl;

	private MessageObject restStatus;

	private List<String> fileOfRest;

	private String restLocation;

	private Date lastModifying;

	public RestAPI() {
		super();
	}

	public RestAPI(@JsonProperty("restId") String restId, @JsonProperty("restName") String restName,
			@JsonProperty("restUrl") String restUrl, @JsonProperty("restStatus") MessageObject restStatus,
			@JsonProperty("restfileOfRest") List<String> fileOfRest, @JsonProperty("restLocation") String restLocation,
			@JsonProperty("lastModifying") Date lastModifying) {
		this.restId = restId;
		this.restName = restName;
		this.restUrl = restUrl;
		this.restStatus = restStatus;
		this.fileOfRest = fileOfRest;
		this.restLocation = restLocation;
		this.lastModifying = lastModifying;

	}

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
