/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CartItem;
import dto.Discount;
import dto.OrderItem;
import dto.OrderTable;
import dto.ShoppingCart;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raiku
 */
public class OrderTableDAO extends MyConnection {

    public OrderTableDAO() {
        getEntityManager();
    }

    public OrderTable addNewOrder(OrderTable o, ShoppingCart s, Discount d) throws Exception {
        try {
            getEntityManager();
            o.setStatus("pending");
            o.setOrderDate(new Date());
            if (d != null) {
                o.setDiscountCode(d.getCode());
            }
            entityManager.getTransaction().begin();
            entityManager.persist(o);
            entityManager.flush();
            entityManager.getTransaction().commit();
            double total = updateOrderItemList(o, s) + 10;
            if (d != null) {
                total = total - d.getDiscountAmount() - (total * d.getDiscountPercent() / 100);
            }
            o.setTotalAmount(total);
            o.setShipFee(10);
            getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(o);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            closeConnect();
        }
        return o;
    }

    public double updateOrderItemList(OrderTable o, ShoppingCart s) {
        try {
            getEntityManager();
            List<OrderItem> orderList = new ArrayList<OrderItem>();
            List<CartItem> cartList = s.getCartItemList();
            double total = 0;
            entityManager.getTransaction().begin();
            for (CartItem c : cartList) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(o);
                orderItem.setProductId(c.getProductId());
                orderItem.setQuantity(c.getQuantity());
                orderList.add(orderItem);
                entityManager.persist(orderItem);
                o.getOrderItemList().add(orderItem);
                total += c.getProductId().getPrice() * c.getQuantity();
            }
            entityManager.flush();
            entityManager.getTransaction().commit();
            return total;
        } finally {
            closeConnect();
        }
    }

    public List<OrderTable> loadOrderTable() {
        TypedQuery query = entityManager.createNamedQuery("OrderTable.loadOrder", OrderTable.class);
        return query.getResultList();
    }

    public List<OrderTable> loadOrderTableFollow(String s) {
        try {
            getEntityManager();
            TypedQuery query;
            if (s.equals("paypal")) {
                query = entityManager.createNamedQuery("OrderTable.loadOrderByPaymentMethod", OrderTable.class);
                query.setParameter("paymentMethod", "PayPal");
                return query.getResultList();
            } else if (s.equals("direct")) {
                query = entityManager.createNamedQuery("OrderTable.loadOrderByPaymentMethod", OrderTable.class);
                query.setParameter("paymentMethod", "direct");
                return query.getResultList();
            } else if (s.equals("bank")) {
                query = entityManager.createNamedQuery("OrderTable.loadOrderByPaymentMethod", OrderTable.class);
                query.setParameter("paymentMethod", "Bank");
                return query.getResultList();
            } else if (s.equals("displayPending")) {
                query = entityManager.createNamedQuery("OrderTable.loadOrderByStatus", OrderTable.class);
                query.setParameter("status", "pending");
                return query.getResultList();
            } else if (s.equals("displayPaid")) {
                query = entityManager.createNamedQuery("OrderTable.loadOrderByStatus", OrderTable.class);
                query.setParameter("status", "paid");
                return query.getResultList();
            }else if (s.equals("displayProcessing")) {
                query = entityManager.createNamedQuery("OrderTable.loadOrderByStatus", OrderTable.class);
                query.setParameter("status", "processing");
                return query.getResultList();
            } else if (s.equals("displayComplete")) {
                query = entityManager.createNamedQuery("OrderTable.loadOrderByStatus", OrderTable.class);
                query.setParameter("status", "completed");
                return query.getResultList();
            }
            return loadOrderTable();
        }finally{
            closeConnect();
        }
    }

    public OrderTable updateOrderStatus(OrderTable o, String status) {
        try {
            getEntityManager();
            o.setStatus(status);
            entityManager.getTransaction().begin();
            entityManager.merge(o);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } finally {
            closeConnect();
        }
        return o;
    }
}
