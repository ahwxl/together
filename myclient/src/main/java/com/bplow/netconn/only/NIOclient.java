package com.bplow.netconn.only;

public class NIOclient implements Command{

    @Override
    public void execute() {
        ClassLoader cl = this.getClass().getClassLoader();
        System.out.println(cl);
        
        AssamblyMessage am = new AssamblyMessage();
        am.assambly();
    }

}
