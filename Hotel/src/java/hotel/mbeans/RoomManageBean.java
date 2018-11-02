/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.mbeans;

import hotel.jsf.util.JsfUtil;
import hotel.repository.RoomRepository;
import hotel.repository.entities.Room;
import hotel.repository.entities.RoomType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author maclee
 */
@ManagedBean(name = "roomManageBean")
@ViewScoped
public class RoomManageBean {

    @EJB
    RoomRepository roomRepository;

    private Room room;
    private Room selected;
    private List<RoomType> roomTypes;
    private RoomType roomType;
    private int roomTypeId;
    private int totalrooms = 0;
    private boolean selectOne = true;
    public static final String CURRENTURL = "room.xhtml";

    public RoomManageBean() {
        room = new Room();
        roomTypes = new ArrayList<>();
    }

    public RoomManageBean(RoomRepository roomRepository, Room room, Room selected, List<RoomType> roomTypes, RoomType roomType, int roomTypeId) {
        this.roomRepository = roomRepository;
        this.room = room;
        this.selected = selected;
        this.roomTypes = roomTypes;
        this.roomType = roomType;
        this.roomTypeId = roomTypeId;
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getSelected() {
        return selected;
    }

    public void setSelected(Room selected) {
        this.selected = selected;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public boolean isSelectOne() {
        return selectOne;
    }

    public void setSelectOne(boolean selectOne) {
        this.selectOne = selectOne;
    }

    public int getTotalrooms() {
        if (this.getAllRoom() != null) {
            this.totalrooms = this.getAllRoom().size();
        }
        return totalrooms;
    }

    public void setTotalrooms(int totalrooms) {
        this.totalrooms = totalrooms;
    }

    public List<Room> getAllRoom() {
        try {
            List<Room> rooms = roomRepository.getAllRooms();
            return rooms;
        } catch (Exception ex) {
            Logger.getLogger(RoomManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addRoom() throws Exception {
        this.room.setRoomType(this.getRoomTypeById(this.roomTypeId));
        roomRepository.addOrder(this.room);
        JsfUtil.redirect(CURRENTURL);
    }

    public void removeRoom() throws Exception {
        Logger.getLogger(RoomManageBean.class.getName()).log(Level.SEVERE, null, roomTypeId);
        roomRepository.removeRoom(this.selected.getRoomId());
        this.selectOne = true;
        JsfUtil.redirect(CURRENTURL);
    }

    public void editRoom() throws Exception {
        roomRepository.editRoom(selected);
        this.selectOne = true;
        JsfUtil.redirect(CURRENTURL);
    }

    public List<RoomType> selectRoomTypes() {
        try {
            //List<Role> roles = new ArrayList<>();
            roomTypes = roomRepository.getAllRoomType();
            return roomTypes;
        } catch (Exception ex) {
            Logger.getLogger(RoomManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public RoomType getRoomTypeById(int id) throws Exception {
        RoomType roomType = roomRepository.searchRoomTypeById(id);
        return roomType;
    }

    public void onSelect(Room room) {
        System.out.println("OnSelect:" + room);
        if (null != room) {
            this.selected = room;
            this.selectOne = false;
        }
    }

    public void onDeselect(Room room) {
        System.out.println("OnDeselect:" + room);
        if (null != room) {
            this.selected = null;
            this.selectOne = true;
        }
    }

}
