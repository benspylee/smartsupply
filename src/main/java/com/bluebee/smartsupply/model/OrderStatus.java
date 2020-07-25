package com.bluebee.smartsupply.model;

import java.io.Serializable;
import java.sql.*;

public class OrderStatus implements Serializable {

    private Integer orderstatusid;
    private Integer orderid;
    private Integer orderstatuscd;
    private Timestamp orderts;
    private Integer status;
     
     public Integer getOrderstatusid(){ 
    return this.orderstatusid;
    }
    public void setOrderstatusid(Integer orderstatusid){ 
    this.orderstatusid=orderstatusid;
    }
    public Integer getOrderid(){ 
    return this.orderid;
    }
    public void setOrderid(Integer orderid){ 
    this.orderid=orderid;
    }
    public Integer getOrderstatuscd(){ 
    return this.orderstatuscd;
    }
    public void setOrderstatuscd(Integer orderstatuscd){ 
    this.orderstatuscd=orderstatuscd;
    }
    public Timestamp getOrderts(){ 
    return this.orderts;
    }
    public void setOrderts(Timestamp orderts){ 
    this.orderts=orderts;
    }
    public Integer getStatus(){ 
    return this.status;
    }
    public void setStatus(Integer status){ 
    this.status=status;
    }


}