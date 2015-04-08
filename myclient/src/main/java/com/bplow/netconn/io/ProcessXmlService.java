package com.bplow.netconn.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessXmlService {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessXmlService.class);
	
	public void readXml() throws IOException{
		SAXReader reader = new SAXReader();
		InputStream in = this.getClass().getResourceAsStream("/requestparam/map.xml");
		try {
			Document document = reader.read(in);
			Node node = document.selectSingleNode( "//map/@sevice" );
			
			//logger.info(node.getText());
			
			
			XPath xpathSelector = DocumentHelper.createXPath("/map[@name='sevice']/text()");
		    //List results = xpathSelector.selectNodes(document);
		    Node node2 = xpathSelector.selectSingleNode(document);
		    
		    logger.info(node2.getText());
		    
		    OutputFormat outformat = OutputFormat.createPrettyPrint();
		    outformat.setEncoding("UTF-8");
		    FileWriter out = new FileWriter("/requestparam/mapb.xml");
		    XMLWriter writer = new XMLWriter(out, outformat);
		    writer.write(document);
		    writer.flush();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		
	}

}
