package com.flchen.seckilldemo.seckilldemo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author feilongchen
 * @since 2018-09-20 11:10 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseControllerTestCase {

	@Autowired
	protected MockMvc mockMvc;

	protected <T> String paseObject2String(T obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected MockHttpServletRequestBuilder populatePostBuilder(String urlTemplate, Object model, Object ...uriVars) throws JsonProcessingException {
		String jsonParams = paseObject2String(model);
		return MockMvcRequestBuilders.post(urlTemplate, uriVars).contentType(MediaType.APPLICATION_JSON).content(jsonParams);
	}

	protected MockHttpServletRequestBuilder populateGetBuilder(String urlTemplate, Object ...uriVars) {
		return MockMvcRequestBuilders.get(urlTemplate, uriVars).contentType(MediaType.APPLICATION_JSON);
	}
}
