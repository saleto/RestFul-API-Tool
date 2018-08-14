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
import org.springframework.web.bind.annotation.RequestParam;
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

	// Test
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		binder.setValidator(valiRestApi);
	}


	@RequestMapping(value = "/{restId}", method = RequestMethod.GET)
	public HttpEntity<RestAPI> getRestApiDetails(@PathVariable("restId") String restId) {

		RestAPI restAPI = new RestAPI();
		restAPI = generateRestApiService.getRestApiData(restId);
		return new ResponseEntity<RestAPI>(restAPI, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<RestAPI>>> searchAllRestApis(
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		List<Link> links = new ArrayList<Link>();
		List<Link> items = new ArrayList<Link>();

		List<RestAPI> restAPIList = new ArrayList<>();
		restAPIList = generateRestApiService.searchRestApiData(size, page, filters);
		if (restAPIList == null || restAPIList.isEmpty()) {
			return new ResponseEntity<Resources<Resource<RestAPI>>>(HttpStatus.NO_CONTENT);
		}

		List<Resource<RestAPI>> RestAPIResource = new ArrayList<>();
		int restId = 0;
		for (RestAPI restAPI : restAPIList) {
			restId++;
			Link activityLink = linkTo(
					methodOn(GenerateRestApiController.class).getRestApiDetails(String.valueOf(restId)))
							.withRel("generateRest");
			Link item = linkTo(methodOn(GenerateRestApiController.class).getRestApiDetails(String.valueOf(restId)))
					.withRel("item");
			items.add(item);

			RestAPIResource.add(new Resource<RestAPI>(restAPI, activityLink));
		}

		if (items.size() > 0) {
			links.addAll(items);
		}

		Resources<Resource<RestAPI>> restAPIResources = new Resources<>(RestAPIResource, links);

		return new ResponseEntity<Resources<Resource<RestAPI>>>(restAPIResources, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody HttpEntity<MessageObject> createRestApiService(@Valid @RequestBody RestAPI restApi) {
		MessageObject messageObj = generateRestApiService.createRestApiData(restApi);
		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);
	}

	@RequestMapping(value = "/{restId}", method = RequestMethod.DELETE)
	public HttpEntity<MessageObject> deleteRestApiService(@PathVariable("restId") String restId) {

		MessageObject messageObj = generateRestApiService.deleteRestApiData(restId);

		RestAPI restAPI = new RestAPI();
		messageObj = generateRestApiService.deleteRestApiData(restId);
		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);
	}

	@RequestMapping(value = "/{restId}", method = RequestMethod.PATCH)
	public HttpEntity<MessageObject> updateRestApiService(@PathVariable("restId") String restId) {
		MessageObject messageObj = generateRestApiService.updateRestApiData(restId);
		return new ResponseEntity<MessageObject>(messageObj, HttpStatus.OK);
	}

}
