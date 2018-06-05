package pb360.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import com.fasterxml.jackson.databind.ObjectMapper;

import pb360.model.JsonDataModel;

public class ReadJson {
	public ReadJson(String link) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonDataModel jsonData = objectMapper.readValue(link, JsonDataModel.class);
			System.out.println(jsonData);
		} catch (IOException e) {
			System.out.println(e);

		}

	}
}
