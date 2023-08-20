/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CartItem;
import dto.Product;
import dto.ShoppingCart;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raiku
 */
public class CartItemDAO extends MyConnection{
    public CartItemDAO() {
    }
    
    public CartItem createCartItem(Product p, ShoppingCart s){
        try{
            getEntityManager();
            CartItem c = new CartItem();
            c.setProductId(p);
            c.setCartId(s);
            c.setQuantity(1);
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            return c;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }finally{
            closeConnect();
        }
    }
    
    public CartItem getCartItemById(int cId){
        try{
            return entityManager.find(CartItem.class, cId);
        }finally{
            closeConnect();
        }
    }
}
