package com.didikeke.spunsugar.client.domain;

public class User {
    
    private String id;
    private String name;
    private String expirationDate;
    
    public User(String id, String name, String validDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = validDate;
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
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
