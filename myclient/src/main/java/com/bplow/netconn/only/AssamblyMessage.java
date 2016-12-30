package com.bplow.netconn.only;

import java.util.HashMap;
import java.util.Map;



public class AssamblyMessage {
    
    public String assambly(){
        
        Map map = new HashMap();
        
        ClassLoader cl = this.getClass().getClassLoader();
        System.out.println(cl);
        
        map.put("www", "1234");
        
        return null;
    }

}
