package pb360.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pb360.model.UserModel;
import pb360.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/registration")
	public String createNewUser(@Valid @RequestBody UserModel user) {

		if (userService.register(user)) {
			return "Good";
		}
		
		return "Account not available";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String fetchUser() {
		return "Can access";
	}
}
