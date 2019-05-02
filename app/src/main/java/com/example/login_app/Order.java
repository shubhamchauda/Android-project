package com.example.login_app;

public class Order
{ String name,table,quantity,hname,itemname  ;

    public Order() {
        String name,table,quantity,hanme ;
    }

    public Order(String name, String table, String quantity,String hname,String itemname) {
        this.name = name;
        this.table = table;
        this.quantity = quantity;
        this.hname = hname;
        this.itemname = itemname;


    }



    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }
}
