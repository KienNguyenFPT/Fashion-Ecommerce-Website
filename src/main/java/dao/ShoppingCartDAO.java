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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raiku
 */
public class ShoppingCartDAO extends MyConnection {

    public ShoppingCartDAO() {
    }

    public ShoppingCart addToCart(int pId, ShoppingCart s) {
        Product p = new ProductDAO().getProductById(pId);
        CartItem c = checkItemExist(pId, s);
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
            System.out.println(e.getMessage());
            return null;
        }
    }

    public CartItem checkItemExist(int pId, ShoppingCart s) {
        for (CartItem c : s.getCartItemList()) {
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
                    removeItem(c);
                    s.getCartItemList().remove(c);
                    break;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CartItemDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return s;
    }

    public ShoppingCart updateQuantity(CartItem c, ShoppingCart s) {
        try {
            getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(c);
            entityManager.flush();
            entityManager.getTransaction().commit();
            for (CartItem cTemp : s.getCartItemList()) {
                if (cTemp.getCartItemId() == c.getCartItemId()) {
                    c.setQuantity(c.getQuantity());
                    break;
                }
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnect();
        }
        return s;
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        try {
            getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(shoppingCart);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return shoppingCart;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnect();
        }
        return null;
    }

    public ShoppingCart loadShoppingCart(Customer cusId) {
        try {
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("ShoppingCart.findByCustomerId", ShoppingCart.class);
            query.setParameter("customerId", cusId);
            List<ShoppingCart> cartList = query.getResultList();
            if (cartList.size() > 0) {
                return cartList.get(0);
            } else {
                ShoppingCart cart = new ShoppingCart();
                cart.setCustomerId(cusId);
                cart = createShoppingCart(cart);
                if (cart != null) {
                    return cart;
                }
            }
        } finally {
            closeConnect();
        }
        return null;
    }

    public ShoppingCart removeAllCartItem(ShoppingCart s) {
        try {
            getEntityManager();
            s.setCartItemList(new ArrayList<CartItem>());
            entityManager.getTransaction().begin();
            TypedQuery query = entityManager.createNamedQuery("CartItem.deleteAllItemInCart", CartItem.class);
            query.setParameter("cartId", s.getCartId());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return s;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return s;
        } finally {
            closeConnect();
        }
    }

    public void removeItem(CartItem c) {
        try {
            getEntityManager();
            entityManager.getTransaction().begin();
            CartItem managedCartItem = entityManager.merge(c);
            entityManager.remove(managedCartItem);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnect();
        }
    }
}
