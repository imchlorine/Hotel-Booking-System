/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository.entities;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yuqi Li
 */
@Entity
@Table(name = "HOTEL_ROOM")
@NamedQueries({
    @NamedQuery(name = "Room.getAll", query = "SELECT r FROM Room r")})
@XmlRootElement
public class Room implements Serializable{
    
    @Id
    @Column(name = "roomid")
    @SequenceGenerator(name="seq_room",sequenceName="seq_room") 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_room")
    private int roomId;
    @Column(name = "roomdesc")
    private String roomDesc;
    private double price;
    @Column(name = "roomavaliable")
    private int roomAvaliable;
    @ManyToOne
    @JoinColumn(name = "roomtype")
    private RoomType roomType;
    @OneToMany(mappedBy = "rooms")
    private Set<Transaction> transactions;

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRoom_avaliable() {
        return roomAvaliable;
    }

    public void setRoom_avaliable(int roomAvaliable) {
        this.roomAvaliable = roomAvaliable;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }  

    public int getRoomAvaliable() {
        return roomAvaliable;
    }

    public void setRoomAvaliable(int roomAvaliable) {
        this.roomAvaliable = roomAvaliable;
    }

    @XmlTransient
    public Set<Transaction> getOrders() {
        return transactions;
    }

    public void setOrders(Set<Transaction> orders) {
        this.transactions = orders;
    }
    
}
