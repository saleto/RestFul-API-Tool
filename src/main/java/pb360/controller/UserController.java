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
<<<<<<< HEAD
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
=======
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b

import pb360.model.MessageObject;
import pb360.model.UserModel;
import pb360.service.UserService;

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
<<<<<<< HEAD
			messageObject.add(linkTo(methodOn(UserController.class).getClass()).withSelfRel());
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}
=======
			messageObject.add(linkTo(methodOn(UserController.class).getClass()).withRel(user.getUsername()));
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b
		return new ResponseEntity<MessageObject>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String fetchUser() {
		return "Can access";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{username}")
<<<<<<< HEAD
	public HttpEntity<MessageObject> loginUser(@PathVariable("username") String username, @RequestBody UserModel user) {
		MessageObject messageObject = userService.login(username, user);
		if (messageObject != null ) {
			messageObject.add(linkTo(methodOn(this.getClass()).getClass()).withRel(username));
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<MessageObject>(messageObject, HttpStatus.NO_CONTENT);
=======
	public HttpEntity<MessageObject> loginUser(@PathVariable("username") String username,
			@RequestBody UserModel userModel) {
		MessageObject messageObject = userService.login(username, userModel);
		if (messageObject != null) {
			messageObject.add(linkTo(methodOn(UserController.class).getClass()).withRel(username));
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<MessageObject>(HttpStatus.NO_CONTENT);
>>>>>>> e86a4b8932c5bc7d31e682745d6d0afa4602f09b
	}
}
