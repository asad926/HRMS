package com.developer101.mavenproject2.model;

public class Locations {
    private String loc_id;
    private String Street_adr;
    private String postle_code;
    private String city;
    private String state;
    private String cun_id;

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getStreet_adr() {
        return Street_adr;
    }

    public void setStreet_adr(String street_adr) {
        Street_adr = street_adr;
    }

    public String getPostle_code() {
        return postle_code;
    }

    public void setPostle_code(String postle_code) {
        this.postle_code = postle_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCun_id() {
        return cun_id;
    }

    public void setCun_id(String cun_id) {
        this.cun_id = cun_id;
    }
}
