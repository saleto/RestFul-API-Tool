package pb360.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pb360.model.MessageObject;
import pb360.model.UserModel;
import pb360.service.UserService;
import pb360.service.impl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/registration")
	public HttpEntity<MessageObject> createNewUser(@Valid @RequestBody UserModel user) {
		MessageObject messageObject = userService.register(user);
		if (messageObject != null) {
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String fetchUser() {
		return "Can access";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{username}/{password}")
	public HttpEntity<MessageObject> loginUser(@Valid @PathVariable String username, @PathVariable String password) {
		MessageObject messageObject = userService.login(username, password);
		if (messageObject != null) {
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
