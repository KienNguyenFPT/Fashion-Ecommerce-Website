/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.MyConnection.closeConnect;
import static dao.MyConnection.entityManager;
import static dao.MyConnection.getEntityManager;
import dto.Product;
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
    
     public List<Product> getProductbySearch(String name){
        try{
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("Product.findByName", Product.class);
            query.setParameter("name", "%" + name + "%");
            List<Product> rs = query.getResultList();
            if (rs.size() > 0){
                return rs;
            }else{
                return null;
            }
        }finally{
            closeConnect();
        }
    }
}
