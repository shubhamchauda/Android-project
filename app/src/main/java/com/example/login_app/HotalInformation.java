package com.example.login_app;

public class HotalInformation {

    String hotelname, ownername, tableno, address;

    public HotalInformation() {
    }

    public HotalInformation(String hotelname, String ownername, String tableno, String address) {
        this.hotelname = hotelname;
        this.ownername = ownername;
        this.tableno = tableno;
        this.address = address;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
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
}
