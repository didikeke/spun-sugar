package com.didikeke.spunsugar.client.domain;

public class Item {
    
    private String id;          //item, hold, history
    private String renewId;     //item
    private String title;       //item, hold, history
    private String barcode;     //item
    private String renewCount;  //item
    private String status;      //item, hold
    private String callNo;      //item
    
    private String author;      //history
    private String date;        //history
    private String details;     //history
    
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
    
}
