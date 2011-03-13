package com.didikeke.spunsugar.client.domain;

public class Reader {
    
    private String id;
    private String name;
    private String validDate;
    
    public Reader(String id, String name, String validDate) {
        this.id = id;
        this.name = name;
        this.validDate = validDate;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

}
