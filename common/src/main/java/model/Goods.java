package model;

import lombok.Data;

//@Data
public class Goods {
    public int gid,gprice;
    public String gname,gdetails;
    private Integer types;
    private String gremain;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGprice() {
        return gprice;
    }

    public void setGprice(int gprice) {
        this.gprice = gprice;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdetails() {
        return gdetails;
    }

    public void setGdetails(String gdetails) {
        this.gdetails = gdetails;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getGremain() {
        return gremain;
    }

    public void setGremain(String gremain) {
        this.gremain = gremain;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gprice=" + gprice +
                ", gname='" + gname + '\'' +
                ", gdetails='" + gdetails + '\'' +
                ", types=" + types +
                ", gremain='" + gremain + '\'' +
                '}';
    }
}
