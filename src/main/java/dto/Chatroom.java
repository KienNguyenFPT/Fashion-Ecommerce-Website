/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raiku
 */
@Entity
@Table(name = "chatroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chatroom.findAll", query = "SELECT c FROM Chatroom c"),
    @NamedQuery(name = "Chatroom.findByRoomId", query = "SELECT c FROM Chatroom c WHERE c.roomId = :roomId"),
    @NamedQuery(name = "Chatroom.findByUser1Id", query = "SELECT c FROM Chatroom c WHERE c.user1Id = :id ORDER BY c.roomId DESC"),
    @NamedQuery(name = "Chatroom.findByUser2Id", query = "SELECT c FROM Chatroom c WHERE c.user2Id = :id OR c.user2Id IS NULL ORDER BY c.roomId DESC"),})
public class Chatroom implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Message> messageList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomId")
    private Integer roomId;
    @JoinColumn(name = "user2Id", referencedColumnName = "admin_id")
    @ManyToOne
    private Admin user2Id;
    @JoinColumn(name = "user1Id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Customer user1Id;

    public Chatroom() {
    }

    public Chatroom(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Admin getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Admin user2Id) {
        this.user2Id = user2Id;
    }

    public Customer getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Customer user1Id) {
        this.user1Id = user1Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chatroom)) {
            return false;
        }
        Chatroom other = (Chatroom) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Chatroom[ roomId=" + roomId + " ]";
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

}
