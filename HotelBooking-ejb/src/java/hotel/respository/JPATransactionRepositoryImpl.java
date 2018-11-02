/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.respository;

import hotel.repository.TransactionRepository;
import hotel.repository.entities.Room;
import hotel.repository.entities.RoomType;
import hotel.repository.entities.Transaction;
import hotel.repository.entities.TransactionType;
import hotel.repository.entities.User;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Yuqi Li
 */
@Stateful
public class JPATransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addTransection(Transaction transaction) throws Exception {
        entityManager.persist(transaction);
        entityManager.flush();
    }

    @Override
    public void addTransectionType(TransactionType transType) throws Exception {
        entityManager.persist(transType);
        entityManager.flush();
    }

    @Override
    public Transaction searchTransactionById(int id) throws Exception {
        Transaction transaction = entityManager.find(Transaction.class, id);
        return transaction;
    }

    @Override
    public TransactionType searchTransactionTypeById(int id) throws Exception {
        TransactionType transactionType = entityManager.find(TransactionType.class, id);
        return transactionType;
    }

    @Override
    public List<Transaction> getAllTransactions() throws Exception {
        return entityManager.createNamedQuery("Transaction.getAll").getResultList();
    }

    @Override
    public void removeTransaction(int transactionId) throws Exception {
        Transaction transaction = this.searchTransactionById(transactionId);

        if (transaction != null) {
            entityManager.remove(transaction);
        }
    }

    @Override
    public void removeTransactionType(int transtypeId) throws Exception {
        TransactionType transType = this.searchTransactionTypeById(transtypeId);

        if (transType != null) {
            entityManager.remove(transType);
        }
    }
    @Override
    public void editTransaction(Transaction transaction) throws Exception {
        entityManager.merge(transaction);
    }

    @Override
    public void editTransactionType(TransactionType transactionType) throws Exception {
        entityManager.merge(transactionType);
    }

    @Override
    public Set<Transaction> searchTransactionByType(TransactionType type) throws Exception {
        type = entityManager.find(TransactionType.class, type.getTypeId());
        type.getTransactions().size();
        entityManager.refresh(type);
        return type.getTransactions();
    }

    @Override
    public List<TransactionType> getAllTypes() throws Exception {
        return entityManager.createNamedQuery("TransactionType.getAll").getResultList();
    }

    @Override
    public List<Transaction> searchTransactionByName(String name) throws Exception {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> c = qb.createQuery(Transaction.class);
        Root<Transaction> p = c.from(Transaction.class);
        Predicate condition = qb.equal(p.get("transName"), name);
        c.where(condition);
        TypedQuery<Transaction> q = entityManager.createQuery(c);
        List<Transaction> result = q.getResultList();
        return result;
    }

    @Override
    public Set<Room> getRoomByType(int id) throws Exception {
        RoomType roomType = entityManager.find(RoomType.class, id);
        roomType.getRooms().size();
        entityManager.refresh(roomType);
        //Random random = new Random();
        //int n = random.nextInt();
        return roomType.getRooms();
    }

    @Override
    public Set<Transaction> searchTransactionByUser(User user) throws Exception {
        user = entityManager.find(User.class, user.getUserId());
        user.getTransactions().size();
        entityManager.refresh(user);
        return user.getTransactions();
    }

}
