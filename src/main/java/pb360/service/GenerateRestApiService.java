package pb360.service;

import java.util.List;

import pb360.model.MessageObject;
import pb360.model.RestAPI;



public interface GenerateRestApiService {
	
	public MessageObject getRestApiData(String id);

	public List<MessageObject> searchRestApiData();

	public MessageObject createRestApiData(RestAPI restApi );

	public MessageObject updateRestApiData(String restId);

	public MessageObject deleteRestApiData(String restId);

}
