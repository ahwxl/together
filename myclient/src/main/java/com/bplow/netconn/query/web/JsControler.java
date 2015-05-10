package com.bplow.netconn.query.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JsControler {
	
	@RequestMapping(value = "/SC/main", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String mainPage(){
		
		
		return "ad/main";
	}
	
	
	@RequestMapping(value = "/SC/base", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
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
	/**
	 * 请求执行方法
	 * execute?id=cnd2_cnd3&c=Sellbuyads_1431227010526721&cnidx=1&ext=7577|1387
	 * 根据 cnidx 来推断目标加载的ad js
	 * 如果有回流  该方法会再被请求一次 cnidx 加1
	 * @param model
	 * @param request
	 * @param respose
	 * @throws IOException
	 */
	@RequestMapping(value = "/SC/execute", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript3(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		
		String exeNum = request.getParameter("c");
		String cnidx  = request.getParameter("cnidx");
		String adName = "";
		String property ="";
		
		if("1".equals(cnidx)){
			adName = "MZADX";
			property = "{\"l\":\"7577\"}";
		}else{
			adName = "YOUDAO";
			property = "{'slotid':'1380'}";
		}
		
		OutputStream os =respose.getOutputStream();
		
		//InputStream in = IOUtils.toInputStream("(function (win, doc) {"+exeNum+"(0,'YOUDAO',{'slotid':'1387'})})(window, document);","UTF-8");;
		
		InputStream in = IOUtils.toInputStream("(function (win, doc) {"
				+ exeNum + "(0,'" + adName
				+ "',"+property+")})(window, document);", "UTF-8");
		
		

		IOUtils.copy(in, os);
		
		os.flush();
		os.close();
	}
	
	@RequestMapping(value = "/SC/{customerName}/js", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript4(@PathVariable("customerName") String customerName,Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
		String custName = customerName;
		OutputStream os =respose.getOutputStream();
		
		InputStream in = this.getClass().getResourceAsStream("/js/"+custName+".js");
		
		//InputStream in = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		os.flush();
		os.close();
	} 
	
	
	@RequestMapping(value = "/SC/MZADX", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript5(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
				
		OutputStream os =respose.getOutputStream();
		
		InputStream in = this.getClass().getResourceAsStream("/js/MZADX.js");
		
		//InputStream in = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		in.close();
		
		os.flush();
		os.close();
	}
	
	@RequestMapping(value = "/SC/YOUDAO", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void obtainScript6(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse respose) throws IOException {
		
		respose.setContentType("text/javascript");
		respose.setCharacterEncoding("UTF-8");
				
		OutputStream os =respose.getOutputStream();
		
		InputStream in = this.getClass().getResourceAsStream("/js/YOUDAO.js");
		
		//InputStream in = IOUtils.toInputStream("(function(a){alert(a)})('你好a')","UTF-8");
		
		IOUtils.copy(in, os);
		
		in.close();
		
		os.flush();
		os.close();
	} 

}
