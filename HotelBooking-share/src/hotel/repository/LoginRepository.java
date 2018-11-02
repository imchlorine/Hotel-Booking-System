/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository;

import hotel.repository.entities.User;
import javax.ejb.Remote;

/**
 *
 * @author maclee
 */
@Remote
public interface LoginRepository {
    
    public User login(String email, String password) throws Exception;
    
}
