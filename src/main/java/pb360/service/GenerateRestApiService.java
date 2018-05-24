package pb360.service;

import pb360.model.MessageObject;

public interface GenerateRestApiService {
	
	public MessageObject getRestApiData(String id);

	public MessageObject searchRestApiData();

	public MessageObject createRestApiData();

	public MessageObject updateRestApiData();

	public MessageObject deleteRestApiData();

}
