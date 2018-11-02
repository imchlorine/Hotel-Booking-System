/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.api;

import hotel.repository.TransactionRepository;
import hotel.repository.UserRepository;
import hotel.repository.entities.Transaction;
import hotel.repository.entities.TransactionType;
import hotel.repository.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author maclee
 */
@Path("transaction")
public class TransactionManageBean {

    @EJB
    TransactionRepository transactionRepository;
    @EJB
    UserRepository userRepository;

    public TransactionManageBean() {
    }

    @GET
    @Path("/getAllTransType")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TransactionType> getAllTransTypes() {
        try {
            List<TransactionType> types = transactionRepository.getAllTypes();
            //String gson = new Gson().toJson(users);
            return types;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @POST
    @Path("/addTransactionType")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public TransactionType addTransactionType(TransactionType transType) throws Exception {
        transactionRepository.addTransectionType(transType);
        return transType;
    }

    @PUT
    @Path("/updatetransactionType/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public TransactionType updateTransactionType(@PathParam("id") int id, TransactionType transactionType) throws Exception {
        transactionType.setTypeId(id);
        transactionRepository.editTransactionType(transactionType);
        return transactionType;
    }

    @DELETE
    @Path("/deleteTransType/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public void deletetransType(@PathParam("id") int id) throws Exception {
        transactionRepository.removeTransactionType(id);
    }

    @GET
    @Path("/getAllTrans")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transaction> getAllTrans() {
        try {
            List<Transaction> trans = transactionRepository.getAllTransactions();
            return trans;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/getTransByNo/{no}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transaction> getTransByNo(@PathParam("no") String no
    ) {
        try {
            List<Transaction> trans = new ArrayList<>();
            Transaction tran = transactionRepository.searchTransactionById(Integer.parseInt(no));
            trans.add(tran);
            return trans;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/getTransByUser/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transaction> getTransByUser(@PathParam("id") String id
    ) {
        try {
            User user = userRepository.searchUserById(Integer.parseInt(id));
            List<Transaction> trans = new ArrayList<>();
            trans.addAll(transactionRepository.searchTransactionByUser(user));
            return trans;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
