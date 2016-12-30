package com.bplow.netconn.base.loader;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bplow.netconn.only.Command;

@Service
public class Wrapper {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object getBean(String name) throws InstantiationException, IllegalAccessException {
        WebAppClassLoader wcl = new WebAppClassLoader(new URL[0]);

        Object result = null;

        Class cl = wcl.loadClass(name);

        Command cm = (Command) cl.newInstance();
        cm.execute();

        return result;

    }

}
