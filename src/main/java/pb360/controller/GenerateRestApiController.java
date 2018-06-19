package pb360.controller;

import pb360.model.MessageObject;
import pb360.model.RestAPI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import pb360.service.GenerateRestApiService;
import pb360.validation.ValidateRestAPI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

@RestController
@RequestMapping("/v1/restsful")
public class GenerateRestApiController {

	@Autowired
	private GenerateRestApiService generateRestApiService;
	private ValidateRestAPI valiRestApi;
	//Test
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		binder.setValidator(valiRestApi);
	}

	@RequestMapping(value = "/{restId}", method = RequestMethod.GET)
	public HttpEntity<MessageObject> getRestApiDetails(@PathVariable("restId") String restId) {
		MessageObject messageObj = generateRestApiService.getRestApiData(restId);
		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<MessageObject>>> searchAllRestApis() {
		List<Link> links = new ArrayList<Link>();
		List<Link> items = new ArrayList<Link>();

		List<MessageObject> messageObjList = new ArrayList<>();
		messageObjList = generateRestApiService.searchRestApiData();
		if (messageObjList == null || messageObjList.isEmpty()) {
			return new ResponseEntity<Resources<Resource<MessageObject>>>(HttpStatus.NO_CONTENT);
		}

		List<Resource<MessageObject>> messageObjResource = new ArrayList<>();
		int restId = 0;
		for (MessageObject messageObj : messageObjList) {
			restId++;
			Link activityLink = linkTo(
					methodOn(GenerateRestApiController.class).getRestApiDetails(String.valueOf(restId)))
							.withRel("generateRest");
			Link item = linkTo(methodOn(GenerateRestApiController.class).getRestApiDetails(String.valueOf(restId)))
					.withRel("item");
			items.add(item);

			messageObjResource.add(new Resource<MessageObject>(messageObj, activityLink));
		}

		if (items.size() > 0) {
			links.addAll(items);
		}

		Resources<Resource<MessageObject>> messageObjResources = new Resources<>(messageObjResource, links);

		return new ResponseEntity<Resources<Resource<MessageObject>>>(messageObjResources, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody HttpEntity<MessageObject> createRestApiService(@Valid @RequestBody RestAPI restApi) {
		MessageObject messageObj = generateRestApiService.createRestApiData(restApi);
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
