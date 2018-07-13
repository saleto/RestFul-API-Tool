package pb360.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class MessageObject extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private String messageNumber;


	private String type;
	private String data;
	private Date datetime;

	public MessageObject() {

	}

	public MessageObject(@JsonProperty("datetime") Date datetime, @JsonProperty("type") String type,
			@JsonProperty("data") String data, @JsonProperty("messageNumber")String messageNumber) {

		this.datetime = datetime;
		this.type = type;
		this.data = data;
	}

	public MessageObject(String type, String data) {
		this.type = type;
		this.data = data;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(String messageNumber) {
		this.messageNumber = messageNumber;
	}

}
