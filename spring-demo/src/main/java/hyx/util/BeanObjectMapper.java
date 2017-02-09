package hyx.util;

import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class BeanObjectMapper {
	private static ObjectMapper mapper = new ObjectMapper();

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static void serMapper(ObjectMapper mapper) {
		BeanObjectMapper.mapper = mapper;
	}

	static {
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		mapper.setTimeZone(TimeZone.getDefault());
		mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,false);
		mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, false);
		mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
	}
}
