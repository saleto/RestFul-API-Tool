package pb360.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import pb360.data.entity.UserEntity;
import pb360.data.repository.UserRepository;
import pb360.model.MessageObject;
import pb360.model.UserModel;
import pb360.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/v1/restful")
public class UserController {

	@Autowired
	private UserService userService;

	private static final int pageNumber = 0;
	private static final int pageSize = 10;

	// @RequestMapping(method = RequestMethod.GET, value = "/restful", params = {
	// "page", "size" })
	// public Page<UserEntity> findPaginated(@RequestParam("page") int page,
	// @RequestParam("size") int size)
	// throws Exception {
	// Page<UserEntity> resultPage = userService.findPaginated(page, size);
	// if (page > resultPage.getTotalPages()) {
	// return null;
	// }
	//
	// return resultPage;
	// }

	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<List<UserEntity>> searchUsers(@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {

		if (page == null) {
			page = pageNumber;
		}
		if (size == null) {
			size = pageSize;
		}
		List<UserEntity> userEntities = userService.findAllUser(filters, pageNumber, pageSize);
		if (userEntities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			ArrayList<Link> links = new ArrayList<>();
			
			userEntities.forEach(user -> user
					.add(linkTo(methodOn(UserController.class).searchUsers(filters, page, size)).withSelfRel()));
		}

		return new ResponseEntity<>(userEntities, HttpStatus.OK);	

	}

	@RequestMapping(method = RequestMethod.POST, value = "/registration")
	public HttpEntity<MessageObject> createNewUser(@Valid @RequestBody UserModel user) {
		MessageObject messageObject = userService.register(user);
		if (messageObject != null) {
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<MessageObject>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{username}")
	public HttpEntity<MessageObject> loginUser(@PathVariable("username") String username,
			@RequestBody UserModel userModel) {
		MessageObject messageObject = userService.login(username, userModel);
		if (messageObject != null) {
			messageObject.add(linkTo(methodOn(UserController.class).loginUser(username, userModel)).withSelfRel());
			return new ResponseEntity<MessageObject>(messageObject, HttpStatus.OK);
		}

		return new ResponseEntity<MessageObject>(HttpStatus.NO_CONTENT);
	}
}
