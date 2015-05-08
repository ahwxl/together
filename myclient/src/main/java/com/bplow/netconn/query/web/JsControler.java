package com.bplow.netconn.query.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class JsControler {
	
	@RequestMapping(value = "/SC/main", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String mainPage(){
		
		
		return "ad/main";
	}
	
	
	@RequestMapping(value = "/SC/start", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		
		OutputStream os =respose.getOutputStream();
		
		InputStream in = this.getClass().getResourceAsStream("/js/base.min.js");
		
		
		//InputStream im = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		os.flush();
		os.close();

	}
	
	
	@RequestMapping(value = "/SC/over", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript2(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		
		OutputStream os =respose.getOutputStream();
		
		InputStream in = this.getClass().getResourceAsStream("/js/adcommp.min.js");
		
		
		//InputStream im = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		os.flush();
		os.close();

	}
	

}
