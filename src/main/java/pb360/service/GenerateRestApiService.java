package pb360.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pb360.data.entity.RestApi;
import pb360.model.MessageObject;
import pb360.model.RestAPI;

public interface GenerateRestApiService  {

	public RestAPI getRestApiData(String restId);

	public List<RestAPI> searchRestApiData(Integer pageSize,Integer pageNumber ,String filters);

	public MessageObject createRestApiData(RestAPI restApi);

	public MessageObject updateRestApiData(String restId);

	public MessageObject deleteRestApiData(String restId);
	

}
