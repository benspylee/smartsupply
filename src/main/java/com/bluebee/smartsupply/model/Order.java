package com.bluebee.smartsupply.model;

import java.io.Serializable;
import java.sql.*;

public class Order implements Serializable {

    private Integer orderid;
    private Integer orderby;
    private Integer orderto;
    private Integer shopcd;
    private Timestamp orderdt;
    private Integer status;
     
     public Integer getOrderid(){ 
    return this.orderid;
    }
    public void setOrderid(Integer orderid){ 
    this.orderid=orderid;
    }
    public Integer getOrderby(){ 
    return this.orderby;
    }
    public void setOrderby(Integer orderby){ 
    this.orderby=orderby;
    }
    public Integer getOrderto(){ 
    return this.orderto;
    }
    public void setOrderto(Integer orderto){ 
    this.orderto=orderto;
    }
    public Integer getShopcd(){ 
    return this.shopcd;
    }
    public void setShopcd(Integer shopcd){ 
    this.shopcd=shopcd;
    }
    public Timestamp getOrderdt(){ 
    return this.orderdt;
    }
    public void setOrderdt(Timestamp orderdt){ 
    this.orderdt=orderdt;
    }
    public Integer getStatus(){ 
    return this.status;
    }
    public void setStatus(Integer status){ 
    this.status=status;
    }


}