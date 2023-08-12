/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.MyConnection.entityManager;
import dto.CartItem;
import dto.Customer;
import dto.Product;
import dto.ShoppingCart;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.exceptions.TransactionException;

/**
 *
 * @author Raiku
 */
public class ShoppingCartDAO extends MyConnection {

    public ShoppingCartDAO() {
        getEntityManager();
    }

    public ShoppingCart addToCart(int pId, ShoppingCart s) {
        Product p = new ProductDAO().getProductById(pId);
        CartItem c = this.checkItemExist(pId, s);
        try {
            if (c != null) {
                c.setQuantity(c.getQuantity() + 1);
                s = updateQuantity(c, s);
                return s;
            } else {
                c = new CartItemDAO().createCartItem(p, s);
                s.getCartItemList().add(c);
                return s;
            }
        } catch (Exception e) {
            String errorMessage = "";
            if (e instanceof NullPointerException) {
                errorMessage = "The product could not be found.";
            } else if (e instanceof EntityExistsException) {
                errorMessage = "The cart item already exists.";
            } else if (e instanceof TransactionException) {
                errorMessage = "There was a problem with the database transaction.";
            } else {
                errorMessage = "An unknown error occurred.";
            }
            entityManager.getTransaction().rollback();
            System.out.println(errorMessage);
            return null;
        }
    }

    public CartItem checkItemExist(int pId, ShoppingCart s) {
        List<CartItem> cartList = s.getCartItemList();
        for (CartItem c : cartList) {
            if (c.getProductId().getProductId() == pId) {
                return c;
            }
        }
        return null;
    }

    public ShoppingCart removeCartItem(int pId, ShoppingCart s) {
        try {
            for (CartItem c : s.getCartItemList()) {
                if (c.getProductId().getProductId() == pId) {
                    s.getCartItemList().remove(c);
                    entityManager.getTransaction().begin();
                    CartItem managedCartItem = entityManager.merge(c);
                    entityManager.remove(managedCartItem);
                    entityManager.getTransaction().commit();
                    return s;
                }
            }
        } catch (Exception e) {
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(CartItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ShoppingCart updateQuantity(CartItem c, ShoppingCart s) {
        try {
            getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(c);
            entityManager.getTransaction().commit();
            List<CartItem> cartList = s.getCartItemList();
            for (CartItem cTemp : cartList) {
                if (cTemp.getCartItemId() == c.getCartItemId()) {
                    c.setQuantity(c.getQuantity());
                    return s;
                }
            }
        }catch(Exception e){
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(shoppingCart);
            entityManager.getTransaction().commit();
            return shoppingCart;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    public ShoppingCart loadShoppingCart(Customer cusId) {
        TypedQuery query = entityManager.createNamedQuery("ShoppingCart.findByCustomerId", ShoppingCart.class);
        query.setParameter("customerId", cusId);
        List<ShoppingCart> cartList = query.getResultList();
        try {
            if (cartList.size() > 0) {
                return cartList.get(0);
            } else {
                ShoppingCart cart = new ShoppingCart();
                cart.setCustomerId(cusId);
                cart = createShoppingCart(cart);
                if (cart != null) {
                    System.out.println(cart.toString());
                    return cart;
                }
            }
        } finally {
            closeConnect();
        }
        return null;
    }

    public ShoppingCart getShoppingCartById(int cartId) {
        return entityManager.find(ShoppingCart.class, cartId);
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return entityManager.createQuery("SELECT sc FROM ShoppingCartDTO sc").getResultList();
    }

    public void saveShoppingCart(ShoppingCart shoppingCart) {
        entityManager.persist(shoppingCart);
    }

    public void updateShoppingCart(ShoppingCart shoppingCart) {
        entityManager.merge(shoppingCart);
    }

    public void deleteShoppingCart(int cartId) {
        ShoppingCart shoppingCart = getShoppingCartById(cartId);
        entityManager.remove(shoppingCart);
    }
}
