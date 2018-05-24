package pb360.model;

import org.springframework.hateoas.ResourceSupport;
import java.util.Date;

public class MessageObject extends ResourceSupport {

	String type;
	String data;
	Date datetime;

	public MessageObject() {
	}

	public MessageObject(Date datetime, String type, String data) {

		this.datetime = datetime;
		this.type = type;
		this.data = data;
	}
	
	public MessageObject(String type, String data)
	{
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

}
