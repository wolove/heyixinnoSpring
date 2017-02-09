package hyx;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hyx.util.BeanObjectMapper;

public class SimpleIntergrateTest extends BaseTest {

	private static ObjectMapper mapper = BeanObjectMapper.getMapper();

	protected <T> T fromJson(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json.getBytes(), clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
