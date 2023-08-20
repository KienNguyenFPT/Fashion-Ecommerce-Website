/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Raiku
 */
public class OrderTableModel {
    private Integer orderId;
    private Date orderDate;
    private double totalAmount;
    private String paymentMethod;
    private String status;
    private Integer customerId;

    public OrderTableModel(Integer orderId, Date orderDate, double totalAmount, String paymentMethod, String status, Integer customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.customerId = customerId;
    }
    
}
