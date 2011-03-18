package com.didikeke.spunsugar.client.domain;

public class Item {
    
    private String id;
    private String renewId;
    private String title;
    private String barcode;
    private String renewCount;
    private String status;
    private String callNo;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRenewId() {
        return renewId;
    }
    public void setRenewId(String renewId) {
        this.renewId = renewId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getRenewCount() {
        return renewCount;
    }
    public void setRenewCount(String renewCount) {
        this.renewCount = renewCount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCallNo() {
        return callNo;
    }
    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }
    
    
    
}
