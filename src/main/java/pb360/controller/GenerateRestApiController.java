package pb360.controller;

import pb360.model.MessageObject;
import pb360.service.GenerateRestApiService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/controllers")
public class GenerateRestApiController {

	@Autowired
	private GenerateRestApiService generateRestApiService;
	
	@InitBinder
	

	@RequestMapping(value = "/{restId}", method = RequestMethod.GET)
	public HttpEntity<MessageObject> getRestApiDetails(@PathVariable("restId") String restId) {

		MessageObject messageObj = generateRestApiService.getRestApiData(restId);

		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<MessageObject> searchAllRestApis() {

		return null;
	}
}
