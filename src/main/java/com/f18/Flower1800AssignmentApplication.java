package com.f18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Flower1800AssignmentApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Flower1800AssignmentApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		//Read the json response
		//List<EmployeeBean> employeeList = readJSONFeeds();
		//System.out.println("employeeList size="+employeeList.size());
		
			
		//List<EmployeeBean> modifiedJSON = modifyElementInJSON(employeeList);
		//System.out.println("employeeList after modification="+modifiedJSON);
	}

	
	private static List<EmployeeBean> readJSONFeeds()
	{
	    final String uri = "http://jsonplaceholder.typicode.com/posts";
	 	        
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<EmployeeBean>> response = restTemplate.exchange(uri, HttpMethod.GET,
	    									null,new ParameterizedTypeReference<List<EmployeeBean>>(){});
	    List<EmployeeBean> employees = response.getBody();
	    return employees;
	}
		
	private static List<EmployeeBean> modifyElementInJSON(List<EmployeeBean> input){
		input.get(3).setTitle("1800Flowers");
		input.get(3).setBody("1800Flowers");
        return input;
    }

	
}
