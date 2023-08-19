/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Raiku
 */
public class MyConnection implements Serializable {

    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.raiyugi_ShopOnline_war_1.0-SNAPSHOTPU");
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public static void closeConnect() {
        entityManager.close();
        entityManagerFactory.close();
    }
}   
