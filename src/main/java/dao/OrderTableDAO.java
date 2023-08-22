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
        double total = 0;
        try {
            getEntityManager();
            List<OrderItem> orderList = new ArrayList<OrderItem>();
            List<CartItem> cartList = s.getCartItemList();
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
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return total;
        } finally {
            closeConnect();
        }
    }

    public List<OrderTable> loadOrderTable(int offset) {
        try {
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("OrderTable.loadOrder", OrderTable.class);
            query.setMaxResults(10);
            query.setFirstResult(offset);
            return query.getResultList();
        } finally {
            closeConnect();
        }
    }

    public List<OrderTable> loadOrderByStatus(String status, int offset) {
        try {
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("OrderTable.loadOrderByStatus", OrderTable.class);
            query.setParameter("status", status);
            query.setMaxResults(10);
            query.setFirstResult(offset);
            return query.getResultList();
        } finally {
            closeConnect();
        }
    }

    public List<OrderTable> loadOrderByMethod(String method, int offset) {
        try {
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("OrderTable.loadOrderByPaymentMethod", OrderTable.class);
            query.setParameter("paymentMethod", method);
            query.setMaxResults(10);
            query.setFirstResult(offset);
            return query.getResultList();
        } finally {
            closeConnect();
        }
    }

    public List<OrderTable> loadOrderByStatusAndMethod(String method, String status, int offset) {
        try {
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("OrderTable.loadOrderByMethodAndStatus", OrderTable.class);
            query.setParameter("paymentMethod", method);
            query.setParameter("status", status);
            query.setMaxResults(10);
            query.setFirstResult(offset);
            return query.getResultList();
        } finally {
            closeConnect();
        }
    }

    public long loadTotalOrderByMethod(String method) {
        try {
            getEntityManager();
            return entityManager.createNamedQuery("OrderTable.countByMethod", Long.class).setParameter("paymentMethod", method).getSingleResult();
        } finally {
            closeConnect();
        }
    }

    public long loadTotalOrderByStatus(String status) {
        try {
            getEntityManager();
            return entityManager.createNamedQuery("OrderTable.countByStatus", Long.class).setParameter("status", status).getSingleResult();
        } finally {
            closeConnect();
        }
    }

    public long loadTotalOrder() {
        try {
            getEntityManager();
            return entityManager.createNamedQuery("OrderTable.countAll", Long.class).getSingleResult();
        } finally {
            closeConnect();
        }
    }

    public long loadTotalOrderByMethodAndStatus(String method, String status) {
        try {
            getEntityManager();
            return entityManager.createNamedQuery("OrderTable.countByStatusAndMethod", Long.class).setParameter("status", status).setParameter("paymentMethod", method).getSingleResult();
        } finally {
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
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return o;
    }
}
