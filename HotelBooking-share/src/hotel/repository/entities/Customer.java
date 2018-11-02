/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author maclee
 */
@Entity
@DiscriminatorValue(value = "C")
public class Customer extends User {

    @Column(name = "balance")
    private double balance;

    public Customer() {
        this.balance=0.00;
    }

    public Customer(double balance, int userId, String username, String lastName, String firstName, String email, String password, String street, String city, String country, String postCode, String phone, String userType,String typeCode, Set<Transaction> transactions) {
        super(userId, username, lastName, firstName, email, password, street, city, country, postCode, phone, userType, typeCode, transactions);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" + "balance=" + balance + '}';
    }
}
