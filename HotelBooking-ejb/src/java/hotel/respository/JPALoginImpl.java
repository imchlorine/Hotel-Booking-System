/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.respository;

import hotel.repository.LoginRepository;
import hotel.repository.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maclee
 */
@Stateless
public class JPALoginImpl implements LoginRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User login(String email, String password) throws Exception {
        try {
            User u = (User) entityManager.createNamedQuery("User.login").setParameter("email", email).setParameter("password", password).getSingleResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (Exception e) {
            return null;
        }       
    }
}
