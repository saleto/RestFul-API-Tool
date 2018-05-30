package pb360.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pb360.model.RestAPI;

public class JacksonObjectMapper {
	public static void main(String[] args) throws IOException{
//		byte[] jsonData = Files.readAllBytes(Paths.get("D:/restapi/JSON_Sample.json"));
//		ObjectMapper objectMapper = new ObjectMapper();
//		RestAPI restApi = objectMapper.readValue(jsonData, RestAPI.class);
//		
//		System.out.println("RestAPI Object\n"+restApi);
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		RestAPI restApi =objectMapper.readValue(new File("D:\\restapi\\JSON_Sample.json"), RestAPI.class);
		}
		catch(JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


}
