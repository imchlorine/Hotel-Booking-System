/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.respository;

import hotel.repository.RoomRepository;
import hotel.repository.entities.Room;
import hotel.repository.entities.RoomType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maclee
 */
@Stateless
public class JPARoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addOrder(Room room) throws Exception {
        entityManager.persist(room);
        entityManager.flush();
    }

    @Override
    public Room searchRoomById(int id) throws Exception {
        Room room = entityManager.find(Room.class, id);
        return room;
    }

    @Override
    public List<Room> getAllRooms() throws Exception {
        return entityManager.createNamedQuery("Room.getAll").getResultList();
    }

    @Override
    public void removeRoom(int roomId) throws Exception {
        Room room = this.searchRoomById(roomId);

        if (room != null) {
            entityManager.remove(room);
        }
    }

    @Override
    public void editRoom(Room room) throws Exception {
        entityManager.merge(room);
    }

    @Override
    public RoomType searchRoomTypeById(int id) throws Exception {
        RoomType roomType = entityManager.find(RoomType.class, id);
        return roomType;
    }

    @Override
    public List<RoomType> getAllRoomType() throws Exception {
        return entityManager.createNamedQuery("RoomType.getAll").getResultList();
    }

}
