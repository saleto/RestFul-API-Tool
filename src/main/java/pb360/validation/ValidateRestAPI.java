package pb360.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import pb360.model.MessageObject;
import pb360.model.RestAPI;

@Component
public class ValidateRestAPI implements org.springframework.validation.Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RestAPI.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
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

		if (restApi.getRestUrl() == null) {
			messageObj.setType("Missing Field");
			messageObj.setData("restUrl");
		}

		if (messageObj.getData() != null && !messageObj.getData().isEmpty()) {
			errors.rejectValue(messageObj.getData(), messageObj.getType(), null);
		}
	}

}
