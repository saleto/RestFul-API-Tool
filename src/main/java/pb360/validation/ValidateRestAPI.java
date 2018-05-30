package pb360.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import pb360.model.MessageObject;
import pb360.model.RestAPI;
@Component 
public class ValidateRestAPI implements org.springframework.validation.Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {

		// TODO Auto-generated method stub
		return RestAPI.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MessageObject messageObj = new MessageObject();
		RestAPI restApi = (RestAPI) target;

		if (restApi.getRestId() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("restId");
		}

		if (restApi.getRestName() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("restName");
		}

		if (restApi.getRestStatus() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("restStatus");
		}

		if (restApi.getRestUrl() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("restUrl");
		}

		if (restApi.getRestLocation() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("restLocation");
		}

		if (restApi.getLastModifying() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("lastModifying");
		}

		if (restApi.getFileOfRest() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("fileOfRest");
		}

		if (messageObj.getData() != null && !messageObj.getData().isEmpty()) {
			errors.rejectValue(messageObj.getData(), messageObj.getType(), null);
		}

	}

}
