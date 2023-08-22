/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Admin;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raiku
 */
public class AdminDAO extends MyConnection{

    public AdminDAO() {
    }
    
    
     public Admin findAdminByIdAndPw(String id, String pw) {
          try{
            getEntityManager();
            TypedQuery query = entityManager.createNamedQuery("Admin.findByIdAndPw", Admin.class);
            query.setParameter("username", id);
            query.setParameter("password", pw);
            List<Admin> rs = query.getResultList();
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
