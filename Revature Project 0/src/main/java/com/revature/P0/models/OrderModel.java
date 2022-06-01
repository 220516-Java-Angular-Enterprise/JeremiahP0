package com.revature.P0.models;


import java.time.LocalDate;
import java.util.Date;


public class OrderModel {
    private String orderid;
    private String itemupc;
    private int quantity;
    private double total;
    private String userid;
    private Date date;


    public OrderModel(){
        super();
    }
    public OrderModel(String orderid, String itemupc, int quantity, double total, String userid,Date date) {
        this.orderid = orderid;
        this.itemupc = itemupc;
        this.quantity = quantity;
        this.total = total;
        this.userid = userid;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getItemupc() {
        return itemupc;
    }

    public void setItemupc(String itemupc) {
        this.itemupc = itemupc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserid() {
        UserModel userModel = new UserModel();
        userModel.setId(getUserid());


        return userModel.getUserId();
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return
                "orderid: " + orderid  +
                " itemupc: " + itemupc +
                " quantity: " + quantity +
                ", total=" + total +
                ", userid='" + userid ;
    }
}
