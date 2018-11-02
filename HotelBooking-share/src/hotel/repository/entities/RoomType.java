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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yuqi Li
 */
@Entity
@Table(name = "HOTEL_ROOM_TYPE")
@NamedQueries({
    @NamedQuery(name = "RoomType.getAll", query = "SELECT r FROM RoomType r")})
@XmlRootElement
public class RoomType implements Serializable {

    @Id
    @Column(name = "typeid")
    private int typeId;

    @Column(name = "typename")
    private String typeName;

    @Column(name = "typedesc")
    private String typeDesc;
    
    @OneToMany(mappedBy = "roomType")
    private Set<Room> rooms;
    
    public RoomType() {
      
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @XmlTransient
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
    
    
}
