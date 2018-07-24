package pb360.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

import pb360.data.entity.UserEntity;
import pb360.model.MessageObject;
import pb360.model.UserModel;
import pb360.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/restful", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pageable> readPageable(@NotNull final Pageable pageable) {
		return ResponseEntity.ok(pageable);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public List<UserEntity> getAllUsers() {
		return userService.findAllUser();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/registration")
	public HttpEntity<MessageObject> createNewUser(@Valid @RequestBody UserModel user) {
		MessageObject messageObject = userService.register(user);
		if (messageObject != null) {
			messageObject.add(linkTo(methodOn(UserController.class).getClass()).withRel(user.getUsername()));
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<MessageObject>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{username}")
	public HttpEntity<MessageObject> loginUser(@PathVariable("username") String username,
			@RequestBody UserModel userModel) {
		MessageObject messageObject = userService.login(username, userModel);
		if (messageObject != null) {
			messageObject.add(linkTo(methodOn(UserController.class).getClass()).withRel(username));
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<MessageObject>(HttpStatus.NO_CONTENT);
	}
}
