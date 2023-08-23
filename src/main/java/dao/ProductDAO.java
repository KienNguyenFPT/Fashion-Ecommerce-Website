/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.MyConnection.closeConnect;
import static dao.MyConnection.entityManager;
import static dao.MyConnection.getEntityManager;
import dto.Category;
import dto.Product;
import dto.Seller;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raiku
 */
public class ProductDAO extends MyConnection {

    public ProductDAO() {
    }

    public Product getProductById(int id) {
        try {
            getEntityManager();
            return entityManager.find(Product.class, id);
        } finally {
            closeConnect();
        }
    }

    public List<Product> getProductbySearch(String name) {
        try {
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("Product.findByName", Product.class);
            query.setParameter("name", "%" + name + "%");
            List<Product> rs = query.getResultList();
            if (rs.size() > 0) {
                return rs;
            } else {
                return null;
            }
        } finally {
            closeConnect();
        }
    }

    public Product addNewProduct(String name, int category, String description, int qCurrent, int qSold, int nPrice, int oPrice, String img, Seller seller) {
        try {
            getEntityManager();
            Product p = new Product();
            p.setName(name);
            p.setCategoryId(entityManager.find(Category.class, category));
            p.setDescription(description);
            p.setQuantity(qCurrent);
            if (qSold > 0) {
                p.setQuantitySold(qSold);
            }
            p.setPrice(nPrice);
            if (oPrice > 0) {
                p.setOldPrice(oPrice);
            }
            p.setImg(img);
            p.setSellerId(seller);
            entityManager.getTransaction().begin();
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            return p;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            closeConnect();
        }
    }
}
