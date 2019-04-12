package com.example.login_app;

public class HoteInfo {
    String hotelname, ownername, tableno, address;


    public HoteInfo(String address, String hotelname, String ownername, String tableno) {
        this.hotelname = hotelname;
        this.ownername = ownername;
        this.address = address;
        this.tableno = tableno;
    }


    public HoteInfo() {

        String hotelname, ownername, tableno, address;
    }

    public String getHotelname() {
        return hotelname;
    }

    public String getOwnername() {
        return ownername;
    }

    public String getTableno() {
        return tableno;
    }

    public String getAddress() {
        return address;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
