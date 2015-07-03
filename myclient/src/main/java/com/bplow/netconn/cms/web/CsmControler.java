package com.bplow.netconn.cms.web;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CsmControler {
	
	
	@RequestMapping(value="/mainScreen",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String mainSrean(Map<String, Object> model , HttpServletResponse respose){
		model.put("cxt", "阿斯顿发放稍等稍等法撒旦法四方达");
		return "cms/mainscreen";
	}

}
