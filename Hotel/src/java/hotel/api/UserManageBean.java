/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.api;

import hotel.repository.TransactionRepository;
import hotel.repository.UserRepository;
import hotel.repository.entities.TransactionType;
import hotel.repository.entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author maclee
 */
@Path("user")
public class UserManageBean {

    @EJB
    UserRepository userRepository;

    public UserManageBean() {
    }

    @GET
    @Path("/getAllUser")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllUser() {
        try {
            List<User> users = userRepository.getAllUsers();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/getUserById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User getUserById(@PathParam("id") String id) {
        try {
            User user = userRepository.searchUserById(Integer.parseInt(id));
                   // getAllUsers().get(Integer.parseInt(id));
            //String gson = new Gson().toJson(users);
            return user;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
