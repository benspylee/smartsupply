package com.bluebee.smartsupply.model;

import java.io.Serializable;

public class Items implements Serializable {

    private String itemname;
    private String itemdesc;
    private String itemId;
    private String prodcatdesc;
    private int prodcat;
    private String url;
    private int imageid;
    private int itemcode;

    public int getItemcode() {
        return itemcode;
    }

    public void setItemcode(int itemcode) {
        this.itemcode = itemcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProdcatdesc() {
        return prodcatdesc;
    }

    public void setProdcatdesc(String prodcatdesc) {
        this.prodcatdesc = prodcatdesc;
    }

    public int getProdcat() {
        return prodcat;
    }

    public void setProdcat(int prodcat) {
        this.prodcat = prodcat;
    }

}
