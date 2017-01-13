package com.bplow.netconn.mock;

import java.util.HashMap;
import java.util.Map;

public class MockMessage {

    private String              outOrderNo;
    private String              tansType;
    private Map<String, Object> fieldMap = new HashMap<String, Object>();

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getTansType() {
        return tansType;
    }

    public void setTansType(String tansType) {
        this.tansType = tansType;
    }

    public Object getField(String fieldName) {
        return fieldMap.get(fieldName);
    }

    public void addField(String key, Object value) {
        fieldMap.put(key, value);
    }

}
