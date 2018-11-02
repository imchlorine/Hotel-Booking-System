/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository;

import hotel.repository.entities.Room;
import hotel.repository.entities.Transaction;
import hotel.repository.entities.TransactionType;
import hotel.repository.entities.User;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 * @autor Yuqi Li
 */
@Remote
public interface TransactionRepository {

    /**
     * Add the transaction being passed as parameter into the repository
     *
     * @param transaction - the transaction to add
     */
    public void addTransection(Transaction transaction) throws Exception;

    /**
     * Search for a transaction by its transaction ID
     *
     * @param id - the transactionId of the transaction to search for
     * @return the transaction found
     */
    public Transaction searchTransactionById(int id) throws Exception;

    /**
     * Return all the transactions in the repository
     *
     * @return all the transactions in the repository
     */
    public List< Transaction> getAllTransactions() throws Exception;

    /**
     * Remove the transaction, whose transaction ID matches the one being passed
     * as parameter, from the repository
     *
     * @param transactionId - the ID of the transaction to remove
     */
    public void removeTransaction(int transactionId) throws Exception;

    /**
     * Update a transaction in the repository
     *
     * @param transaction - the updated information regarding a transaction
     */
    public void editTransaction(Transaction transaction) throws Exception;

    /**
     * Search for transactions by their user
     *
     * @param type - the type that create the transaction
     * @return the transactions found
     */
    public Set<Transaction> searchTransactionByType(TransactionType type) throws Exception;

    /**
     * Return all the roles in the repository
     *
     * @return all the roles in the repository
     */
    public List<TransactionType> getAllTypes() throws Exception;

    /**
     * Search for transactions by name
     *
     * @param name - the type that create the transaction
     * @return the transactions found
     */
    public List<Transaction> searchTransactionByName(String name) throws Exception;

    /**
     * Search for a transactionType by its ID
     *
     * @param id - the transactionId of the transaction to search for
     * @return the transaction found
     */
    public TransactionType searchTransactionTypeById(int id) throws Exception;

    public Set<Room> getRoomByType(int id) throws Exception;

    public Set<Transaction> searchTransactionByUser(User user) throws Exception;

    public void addTransectionType(TransactionType transType) throws Exception;

    public void editTransactionType(TransactionType transactionType) throws Exception;

    public void removeTransactionType(int transtypeId) throws Exception;
}
