package pb360.service;

import java.util.List;

import pb360.model.Registration;

public interface UserService {
	Registration create(Registration user);

	List<Registration> findAll();

}
