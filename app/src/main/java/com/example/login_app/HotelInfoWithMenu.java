package com.example.login_app;

class Menu
{
    String iprice, iname, icategory;

    public String getIprice() {
        return iprice;
    }

    public String getIname() {
        return iname;
    }

    public String getIcategory() {
        return icategory;
    }

    public Menu() {

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

    public Menu(String iname, String iprice, String icategory) {
        this.iprice = iprice;
        this.iname = iname;
        this.icategory = icategory;
    }

}

public class HotelInfoWithMenu
{
    String hotelname, ownername, tableno, address;
    Menu meun ;
    public HotelInfoWithMenu() {
    }

    public HotelInfoWithMenu(String hotelname, String ownername,Menu menu ,String tableno, String address) {
        this.hotelname = hotelname;
        this.ownername = ownername;
        this.tableno = tableno;
        this.address = address;
        this.meun = menu;
    }

    public void setMeun(Menu meun) {
        this.meun = meun;
    }

    public Menu getMeun() {
        return meun;
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
