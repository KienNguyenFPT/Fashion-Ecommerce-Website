/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Admin;
import dto.Chatroom;
import dto.Customer;
import java.util.List;

/**
 *
 * @author Raiku
 */
public class ChatroomDAO extends MyConnection{

    public ChatroomDAO() {
    }
    
    public List<Chatroom> getChatroomOfAdmin(Admin a){
        try{
            getEntityManager();
            return entityManager.createNamedQuery("Chatroom.findByUser2Id", Chatroom.class).setParameter("id", a).getResultList();
        }finally{
            closeConnect();
        }
    }
    
    public Chatroom getChatroomById(int id){
        try{
            getEntityManager();
            return entityManager.find(Chatroom.class, id);
        }finally{
            closeConnect();
        }
    }
    
    public List<Chatroom> getChatroomOfCustomer(Customer c){
        try{
            getEntityManager();
            return entityManager.createNamedQuery("Chatroom.findByUser1Id", Chatroom.class).setParameter("id", c).getResultList();
        }finally{
            closeConnect();
        }
    }
    
    public Chatroom createNewRoom(Customer c){
        try{
            getEntityManager();
            Chatroom room = new Chatroom();
            room.setUser1Id(c);
            entityManager.getTransaction().begin();
            entityManager.persist(room);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return room;
        }finally{
            closeConnect();
        }
    }
    
    public void updateUser2IdChatroom(Admin a, String roomId){
        try{
            getEntityManager();
            Chatroom room = entityManager.find(Chatroom.class, Integer.parseInt(roomId));
            room.setUser2Id(a);
            entityManager.getTransaction().begin();
            entityManager.persist(room);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }finally{
            closeConnect();
        } 
    }
}
