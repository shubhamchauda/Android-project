public class User {
    public String tableno;
    public String hname;
    public String oname;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String tableno, String hname, String oname) {
        this.tableno = tableno;
        this.hname = hname;
        this.oname = oname;

    }

    public String getTableno() {
        return tableno;
    }

    public String getOname() {
        return oname;
    }

    public String getHname() {
        return hname;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }


}