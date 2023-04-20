package model;

import lombok.Data;

import java.util.StringJoiner;

//@Data
public class User {
    private String uaccount,upassword,uname,usex;

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("uaccount='" + uaccount + "'")
                .add("upassword='" + upassword + "'")
                .add("uname='" + uname + "'")
                .add("usex='" + usex + "'")
                .toString();
    }
}
