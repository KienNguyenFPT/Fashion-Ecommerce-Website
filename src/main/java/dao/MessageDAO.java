/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Chatroom;
import dto.Message;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Raiku
 */
public class MessageDAO extends MyConnection{

    public MessageDAO() {
    }

    public Message createNewMessage(int sender, String senderName, String content, Chatroom roomId) {
        try {
            getEntityManager();
            Message mes = new Message();
            mes.setSenderId(sender);
            mes.setSenderName(senderName);
            mes.setContent(content);
            mes.setRoomId(roomId);
            mes.setTimestamp(new Date());
            entityManager.getTransaction().begin();
            entityManager.persist(mes);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return mes;
        }finally{
            closeConnect();
        }
    }
    
    public List<Message> getChatListfromRoomId(int id){
        try{
            getEntityManager();
            return entityManager.createNamedQuery("Message.findByRoomId", Message.class).setParameter("roomId", id).getResultList();
        }finally{
            closeConnect();
        }
    }
}
