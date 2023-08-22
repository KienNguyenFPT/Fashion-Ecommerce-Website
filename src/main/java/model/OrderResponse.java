/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Raiku
 */
public class OrderResponse {
    private List<OrderTableModel> orderResult;
    private long count;

    public OrderResponse(List<OrderTableModel> orderResult, long count) {
        this.orderResult = orderResult;
        this.count = count;
    }

}

