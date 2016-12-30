package com.bplow.netconn.base.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAppClassLoader extends URLClassLoader{
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

    
    public WebAppClassLoader(URL[] urls) {
        super(urls);
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
    
    @Override
    public Class loadClass(String name)  {
        
        Class cl = null;
        try {
            cl = super.loadClass(name);
        } catch (ClassNotFoundException e) {
            logger.error("{}", e);
        }
        
        if(cl == null){
            try {
                cl = findClassInternal(name);
            } catch (Exception e) {
                logger.info("{}",e);
            }
            
        }
        
        return cl;
    }
    
    public Class findClassInternal(String name){
        
        String tempPath = name.replace('.', '/');
        String classPath = tempPath + ".class";
        
        File file = new File("D:/tmp/class/"+classPath);
        FileInputStream st;
        byte[] filebyte = null;
        try {
            st = new FileInputStream(file);
            filebyte = IOUtils.toByteArray(st);
        } catch (FileNotFoundException e) {
            logger.error("{}",e);
        } catch (IOException e) {
            logger.error("{}",e);
        }
        Class clazz = null;
        try {
        clazz = defineClass(name, filebyte, 0,
            filebyte.length);
        } catch (UnsupportedClassVersionError ucve) {
            logger.error("{}",ucve);
        }
        return clazz;
    }

}
