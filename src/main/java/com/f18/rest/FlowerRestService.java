package com.f18.rest;

import java.util.HashSet;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.f18.EmployeeBean;

@RestController
public class FlowerRestService {
	final String uri = "http://jsonplaceholder.typicode.com/posts";
		
	@GetMapping("/getAllRecords")
	public ResponseEntity<List<EmployeeBean>> readJSONFeeds()
	{
		
		//Reading this url
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<EmployeeBean>> response = restTemplate.exchange(uri, HttpMethod.GET,
	    									null,new ParameterizedTypeReference<List<EmployeeBean>>(){});
	    return response;
	}
	
	@GetMapping("/getDuplicateRecords")
	public ResponseEntity<HashSet<EmployeeBean>> getDuplicateRecords()
	{
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<EmployeeBean>> response = restTemplate.exchange(uri, HttpMethod.GET,
	    									null,new ParameterizedTypeReference<List<EmployeeBean>>(){});
	    List<EmployeeBean> employeeList = response.getBody();
	    
	    HashSet<EmployeeBean> duplicates = new HashSet<EmployeeBean>();
        
        for(int i=0, j=i+1 ; j<employeeList.size() ; i++,j++){
            int a = employeeList.get(i).getUserId();
            int b = employeeList.get(j).getUserId();
            if(a==b){
                duplicates.add(employeeList.get(i));
            }             
        }
        
        return new ResponseEntity<HashSet<EmployeeBean>>(duplicates, HttpStatus.OK);
	}
	
	
	@GetMapping("/modifyElementInJSON/{elementNo}")
	public ResponseEntity<List<EmployeeBean>> modifyElementInJSON(@PathVariable("elementNo") int element)
	{
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<EmployeeBean>> response = restTemplate.exchange(uri, HttpMethod.GET,
	    									null,new ParameterizedTypeReference<List<EmployeeBean>>(){});
	    List<EmployeeBean> employeeList = response.getBody();
	    
	    employeeList.get(3).setTitle("1800Flowers");
	    employeeList.get(3).setBody("1800Flowers");
                
        return new ResponseEntity<List<EmployeeBean>>(employeeList, HttpStatus.OK);
	}
	
	
	
	
}
