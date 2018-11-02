/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.respository;

import hotel.repository.UserRepository;
import hotel.repository.entities.User;
import java.util.ArrayList;
import java.util.List;
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
 * @author maclee
 */
@Stateful
public class JPAUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public User searchUserById(int id) throws Exception {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return entityManager.createNamedQuery("User.getAll").getResultList();
    }

    @Override
    public void removeUser(int userId) throws Exception {
        User user = this.searchUserById(userId);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void editUser(User user) throws Exception {
        entityManager.merge(user);
    }

    @Override
    public User searchUserByUserName(String userName) throws Exception {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> c = qb.createQuery(User.class);
        Root<User> u = c.from(User.class);
        Predicate condition = qb.equal(u.get("username"), userName);
        c.where(condition);
        TypedQuery<User> q = entityManager.createQuery(c);
        User result = q.getSingleResult();
        return result;
    }

    @Override
    public List<User> searchUserByMultipleColunm(User user) throws Exception {
        int userId = user.getUserId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String postCode = user.getPostCode();
        String userType = user.getUserType();
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<User> u = cq.from(User.class);
        //Constructing list of parameters
        List<Predicate> predicates = new ArrayList<Predicate>();
        //Adding predicates in case of parameter not being null and ""
        if (userId != 0) {
            predicates.add(
                    qb.equal(u.get("userId"), userId));
        }
        if (firstName != null && !firstName.equals("")) {
            predicates.add(
                    qb.equal(u.get("firstName"), firstName));
        }
        if (lastName != null && !lastName.equals("")) {
            predicates.add(
                    qb.equal(u.get("lastName"), lastName));
        }
        if (email != null && !email.equals("")) {
            predicates.add(
                    qb.equal(u.get("email"), email));
        }
        if (postCode != null && !postCode.equals("")) {
            predicates.add(
                    qb.equal(u.get("postCode"), postCode));
        }
        if (userType != null && !userType.equals("")) {
            predicates.add(
                    qb.equal(u.get("userType"), userType));
        }
        //query itself
        cq.select(u)
                .where(predicates.toArray(new Predicate[]{}));
        //execute query and do something with result
        return entityManager.createQuery(cq).getResultList();
    }

}
