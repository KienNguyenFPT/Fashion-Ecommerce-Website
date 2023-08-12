/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Product;
import javax.persistence.EntityManager;

/**
 *
 * @author Raiku
 */
public class ProductDAO extends MyConnection{

    public ProductDAO() {
        getEntityManager();
    }
    
    public Product getProductById(int id){
        try{
            return entityManager.find(Product.class, id);
        }finally{
            closeConnect();
        }
    }
}
