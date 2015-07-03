package com.bplow.netconn.cms.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleObjectWrapper;

@Service("catalogDirective")
public class CatalogDirective implements TemplateDirectiveModel{

	@Override
	public void execute(Environment arg0, Map arg1, TemplateModel[] arg2,
			TemplateDirectiveBody arg3) throws TemplateException, IOException {
		
		//arg1.put("wxl", "汪小雷");
		arg0.setVariable("wxl",SimpleObjectWrapper.getDefaultInstance().wrap("汪小雷"));
		//arg0.setVariable(name, model);
		arg0.setVariable("ddd", ObjectWrapper.SIMPLE_WRAPPER.wrap("sdd"));
		arg3.render(arg0.getOut());
	}

}
