package pb360.data.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "UserEntity")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String username;
	private String password;
	private String href;

	public UserEntity(String id, String username, String password, String href) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.href = href;
	}

	public UserEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
