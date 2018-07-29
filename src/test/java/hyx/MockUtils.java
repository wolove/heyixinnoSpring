package hyx;

import java.io.UnsupportedEncodingException;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

/**
 * @author hyx
 * 用于包装通用的请求
 */
public class MockUtils {

	private static final String APPLICATION_JSON = "application/json;charset=utf-8";

	public static String mockPost(MockMvc mvc, String uri, String json) throws UnsupportedEncodingException, Exception {
		return mvc
				.perform(
						MockMvcRequestBuilders.post(uri, new Object[] { "json" }).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON).content(checkContent(json).getBytes())).andReturn().getResponse().getContentAsString();
	}

	public static String mockGet(MockMvc mvc, String uri, String json) throws UnsupportedEncodingException, Exception {
		return mvc
				.perform(
						MockMvcRequestBuilders.get(uri, new Object[] { "json" }).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
								.accept(new MediaType[] { MediaType.valueOf("application/json;charset=utf-8") }).content(checkContent(json).getBytes()))
				.andReturn().getResponse().getContentAsString();
	}

	public static String mockPut(MockMvc mvc, String uri, String json) throws UnsupportedEncodingException, Exception {
		return mvc
				.perform(
						MockMvcRequestBuilders.put(uri, new Object[] { "json" }).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
								.accept(new MediaType[] { MediaType.valueOf("application/json;charset=utf-8") }).content(checkContent(json).getBytes()))
				.andReturn().getResponse().getContentAsString();
	}

	public static String mockDelete(MockMvc mvc, String uri, String json) throws UnsupportedEncodingException, Exception {
		return mvc
				.perform(
						MockMvcRequestBuilders.delete(uri, new Object[] { "json" }).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
								.accept(new MediaType[] { MediaType.valueOf("application/json;charset=utf-8") }).content(checkContent(json).getBytes()))
				.andReturn().getResponse().getContentAsString();
	}

	private static String checkContent(String json) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(json)) {
			json = "";
		}
		;
		return json;
	}
}
