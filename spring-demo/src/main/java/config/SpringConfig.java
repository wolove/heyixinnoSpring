package config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import util.BeanObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "controller","aop"})
public class SpringConfig extends WebMvcConfigurerAdapter {

	private static final boolean jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", SpringConfig.class.getClassLoader())
			&& ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", SpringConfig.class.getClassLoader());

	/**
	 * in order to support json-formated message that returned by controller
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		stringConverter.setWriteAcceptCharset(false);
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(stringConverter);
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new SourceHttpMessageConverter());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		
		//object to json mapper
		if (jackson2Present) {
			MappingJackson2HttpMessageConverter convert = new MappingJackson2HttpMessageConverter();
			convert.setObjectMapper(BeanObjectMapper.getMapper());
			ArrayList<MediaType> supportedMediaTypes =new ArrayList<MediaType>();
			supportedMediaTypes.add(MediaType.APPLICATION_JSON);
			convert.setSupportedMediaTypes(supportedMediaTypes);
			converters.add(convert);
		}
	}
}
