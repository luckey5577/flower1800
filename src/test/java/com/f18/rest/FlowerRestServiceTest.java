package com.f18.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlowerRestService.class, secure = false)
public class FlowerRestServiceTest {

	@Autowired
	private MockMvc mvc;
	
	@Mock
	FlowerRestService service;
	
	@Test
	public void testGetAllRecords() throws Exception {
		String uri = "/getAllRecords";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testGetDuplicateRecords() throws Exception {
		String uri = "/getDuplicateRecords";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
			
		String content = mvcResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<EmployeeBean> dupList = Arrays.asList(mapper.readValue(content, EmployeeBean[].class));
		System.out.println("Duplicate Records for userID;s are ="+dupList);
		assertEquals(200, status);
	}
	
	@Test
	public void testModifyElementInJSON() throws Exception {
		String uri = "/modifyElementInJSON/3";
		
		List<EmployeeBean> mocklist = new ArrayList<EmployeeBean>();
		EmployeeBean empOne = new EmployeeBean(1, 1, "John", "howtodoinjava@gmail.com");
		EmployeeBean empTwo = new EmployeeBean(2, 1,  "kolenchiski", "alexk@yahoo.com");
		EmployeeBean empThree = new EmployeeBean(3, 2, "Waugh", "swaugh@gmail.com");
		EmployeeBean empFour = new EmployeeBean(4, 3, "Waugh", "swaugh@gmail.com");
		EmployeeBean empFive = new EmployeeBean(5, 4, "Waugh", "swaugh@gmail.com");
         
		mocklist.add(empOne);
		mocklist.add(empTwo);
		mocklist.add(empThree);
		mocklist.add(empFour);
		mocklist.add(empFive);
         
            
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
			
		
		String content = mvcResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<EmployeeBean> modList = Arrays.asList(mapper.readValue(content, EmployeeBean[].class));
			
		assertEquals(200, status);
	}
}
