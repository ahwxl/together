package com.bplow.netconn.query.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class QueryControler {

	
	@RequestMapping(value="/query",method = RequestMethod.GET)
	public String doQuery(Map<String, Object> model){
		
		
		
		return "config.vm";
	}
	
	@RequestMapping(value="/config.vm",method = RequestMethod.GET)
	public String doConfig(Map<String, Object> model){
		
		
		
		return "";
	}
	
}
