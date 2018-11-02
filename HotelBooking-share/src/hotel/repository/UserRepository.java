/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository;

import hotel.repository.entities.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author maclee
 */
@Remote
public interface UserRepository {
     /**
     * Add the user being passed as parameter into the repository
     *
     * @param user - the user to add
     */
    public void addUser(User user) throws Exception;

    /**
     * Search for a user by its user ID
     *
     * @param id - the userId of the user to search for
     * @return the user found
     */
    public User searchUserById(int id) throws Exception;

     /**
     * Search for a user by its user name
     *
     * @param userName
     * @return the user found
     */
    public User searchUserByUserName(String userName) throws Exception;
    /**
     * Return all the user in the repository
     *
     * @return all the user in the repository
     */
    public List<User> getAllUsers() throws Exception;
    
    /**
     * Remove the user, whose user ID matches the one being passed as parameter, from the repository
     *
     * @param userId - the ID of the transaction to remove
     */
    public void removeUser(int userId) throws Exception;
    
    /**
     * Update a user in the repository
     *
     * @param user - the updated information regarding a transaction
     */
    public void editUser(User user) throws Exception;
    
    public List<User> searchUserByMultipleColunm(User user) throws Exception;
    
    
}
