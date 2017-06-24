package hyx.controller;

import static org.junit.Assert.assertEquals;
import hyx.MockUtils;
import hyx.SimpleIntergrateTest;

import java.util.Map;

import org.junit.Test;

public class TestHelloController extends SimpleIntergrateTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testAll() {
		String uri = "/hello";
		String resStr = "";
		try {
			resStr = MockUtils.mockGet(mockMvc, uri, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> map = fromJson(resStr, Map.class);
		assertEquals("≤‚ ‘ ß∞‹", "greetings", map.get("name"));

	}

}
