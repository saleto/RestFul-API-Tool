package pb360.data.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MessageObject")
public final class MessageObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String type;
	private String data;
	private Date datetime;

	public MessageObject(String type, String data, Date datetime) {
		this.type = type;
		this.data = data;
		this.datetime = datetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
