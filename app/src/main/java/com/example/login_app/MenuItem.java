package com.example.login_app;


public class MenuItem {
    String iprice, iname, icategory;

    MenuItem menuItem;



    public String getIprice() {
        return iprice;
    }

    public String getIname() {
        return iname;
    }

    public String getIcategory() {
        return icategory;
    }

    public MenuItem() {

    }

    public void setIprice(String iprice) {
        this.iprice = iprice;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public void setIcategory(String icategory) {
        this.icategory = icategory;
    }

    public MenuItem(String iname, String iprice, String icategory) {
        this.iprice = iprice;
        this.iname = iname;
        this.icategory = icategory;
    }
}
