/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Customer;
import java.util.List;
import javax.persistence.TypedQuery;

public class CustomerDAO extends MyConnection{

    public CustomerDAO() {
        getEntityManager();
    }

    public Customer findCustomerById(int id) {
        return entityManager.find(Customer.class, id);
    }
    
    public Customer findCustomerByIdAndPw(String id, String pw){
        try{
            TypedQuery query = entityManager.createNamedQuery("Customer.findByIdAndPw", Customer.class);
            query.setParameter("username", id);
            query.setParameter("password", pw);
            List<Customer> rs = query.getResultList();
            if (rs.size() > 0){
                return rs.get(0);
            }else{
                return null;
            }
        }finally{
            closeConnect();
        }
    }

    public void createCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnect();
        }
    }

    // Viết các phương thức khác tại đây (get, update, delete, ...)
}