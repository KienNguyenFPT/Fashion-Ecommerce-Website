/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Seller;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raiku
 */
public class SellerDAO extends MyConnection{
    public SellerDAO() {
    }
    
    
     public Seller findSellerByIdAndPw(String id, String pw) {
          try{
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("Seller.findByIdAndPw", Seller.class);
            query.setParameter("username", id);
            query.setParameter("password", pw);
            List<Seller> rs = query.getResultList();
            if (rs.size() > 0){
                return rs.get(0);
            }else{
                return null;
            }
        }finally{
            closeConnect();
        }
    }
}
