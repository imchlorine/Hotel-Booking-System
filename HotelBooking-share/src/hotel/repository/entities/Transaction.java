/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yuqi Li
 */
@Entity
@Table(name = "HOTEL_TRANSACTION")
@NamedQueries({
    @NamedQuery(name = "Transaction.getAll", query = "SELECT t FROM Transaction t")})
@XmlRootElement
public class Transaction implements Serializable {

    @Id
    @Column(name = "transno")
    @SequenceGenerator(name = "seq_transaction", sequenceName = "seq_transaction")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction")
    private int transNo;
    @Column(name = "transname")
    private String transName;
    @ManyToOne
    @JoinColumn(name = "transTypeid")
    private TransactionType transType;
    @Column(name = "transdesc")
    private String transDesc;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
    @Column(name = "date_start")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column(name = "date_end")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room rooms;
    private String status;

    public Transaction() {
        this.user = new User();
        this.transNo = 0;
        this.transName = "";
        this.transType = new TransactionType();
        this.transDesc = "";
        this.rooms = new Room();
        this.status = "";
        
    }

    public Transaction(int transNo, String transName, TransactionType transType, String transDesc, User user, Date startDate, Date endDate, Room rooms, String status) {
        this.transNo = transNo;
        this.transName = transName;
        this.transType = transType;
        this.transDesc = transDesc;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rooms = rooms;
        this.status = status;
    }

    public int getTransNo() {
        return transNo;
    }

    public void setTransNo(int transNo) {
        this.transNo = transNo;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransactionType getTransType() {
        return transType;
    }

    public void setTransType(TransactionType transType) {
        this.transType = transType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Room getRooms() {
        return rooms;
    }

    public void setRooms(Room rooms) {
        this.rooms = rooms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transNo=" + transNo + ", transName=" + transName + ", transType=" + transType + ", transDesc=" + transDesc + ", user=" + user + ", startDate=" + startDate + ", endDate=" + endDate + ", rooms=" + rooms + ", status=" + status + '}';
    }

   
}
