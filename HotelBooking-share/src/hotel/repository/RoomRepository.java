/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository;

import hotel.repository.entities.Room;
import hotel.repository.entities.RoomType;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author maclee
 */
@Remote
public interface RoomRepository {
    
     /**
     * Add the room being passed as parameter into the repository
     *
     * @param room - the room to add
     */
    public void addOrder(Room room) throws Exception;

    /**
     * Search for a room by its ID
     *
     * @param id - the roomId of the room to search for
     * @return the room found
     */
    public Room searchRoomById(int id) throws Exception;

    /**
     * Return all the room in the repository
     *
     * @return all the room in the repository
     */
    public List< Room> getAllRooms() throws Exception;
    
    /**
     * Remove the room, whose order ID matches the one being passed as parameter, from the repository
     *
     * @param roomId - the ID of the order to remove
     */
    public void removeRoom(int roomId) throws Exception;
    
    /**
     * Update a room in the repository
     *
     * @param room - the updated information regarding a order
     */
    public void editRoom(Room room) throws Exception;
    
    public RoomType searchRoomTypeById(int id) throws Exception;
    
    public List<RoomType> getAllRoomType() throws Exception;
    
}
