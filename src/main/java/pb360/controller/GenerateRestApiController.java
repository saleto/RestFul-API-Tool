package pb360.controller;

import pb360.model.MessageObject;
import pb360.service.GenerateRestApiService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

@RestController
@RequestMapping("/v1/controllers")
public class GenerateRestApiController {

	@Autowired
	private GenerateRestApiService generateRestApiService;

	// @InitBinder

	@RequestMapping(value = "/{restId}", method = RequestMethod.GET)
	public HttpEntity<MessageObject> getRestApiDetails(@PathVariable("restId") String restId) {

		MessageObject messageObj = generateRestApiService.getRestApiData(restId);
		

		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<MessageObject>>> searchAllRestApis() {

		List<MessageObject> messageObjList = generateRestApiService.searchRestApiData();

		return (HttpEntity<Resources<Resource<MessageObject>>>) messageObjList;
	}

	@RequestMapping(value = "/{restId}", method = RequestMethod.POST)
	public HttpEntity<MessageObject> createRestApiService(@PathVariable("restId") String restId) {

		MessageObject messageObj = generateRestApiService.createRestApiData();
		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);

	}

	@RequestMapping(value = "/{restId}", method = RequestMethod.DELETE)
	public HttpEntity<MessageObject> deleteRestApiService(@PathVariable("restId") String restId) {

		MessageObject messageObj = generateRestApiService.deleteRestApiData(restId);
		messageObj = generateRestApiService.getRestApiData(restId);
		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);

	}

	@RequestMapping(value = "/{restId}", method = RequestMethod.PATCH)

	public HttpEntity<MessageObject> updateRestApiService(@PathVariable("restId") String restId) {

		MessageObject messageObj = generateRestApiService.updateRestApiData(restId);
		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);

	}
}
