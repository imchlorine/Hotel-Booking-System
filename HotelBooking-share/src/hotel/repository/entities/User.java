/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.repository.entities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yuqi Li
 */
@Entity
@Table(name = "HOTEL_USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeCode",discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
    @NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    ,
    @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")})
@XmlRootElement
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @Column(name = "userid")
    private int userId;
    @Column(unique = true)
    private String username;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
    @Column(unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "postCode")
    private String postCode;
    @Column(name = "phone")
    private String phone;
    @Column(name = "usertype")
    private String userType;
    @Column(name = "typeCode")
    private String typeCode;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;
    

    public User() {
        this.userId = 0;
        this.username = "";
        this.lastName = "";
        this.firstName = "";
        this.email = "";
        this.password = "";
        this.street = "";
        this.city = "";
        this.country = "";
        this.postCode = "";
        this.phone = "";
        this.userType = "";
        this.typeCode = "";
    }

    public User(int userId, String username, String lastName, String firstName, String email, String password, String street, String city, String country, String postCode, String phone, String userType,String typeCodeString, Set<Transaction> transactions) {
        this.userId = userId;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = this.SHA(password,"SHA-256");
        this.street = street;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.phone = phone;
        this.userType = userType;
        this.typeCode = typeCode;
        this.transactions = transactions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =SHA(password, "SHA-256") ;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @XmlTransient
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String SHA(final String strText, final String strType) {
        String strResult = null;
        if (strText != null && strText.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(strText.getBytes());
                byte byteBuffer[] = messageDigest.digest();
                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password + ", street=" + street + ", city=" + city + ", country=" + country + ", postCode=" + postCode + ", phone=" + phone + ", userType=" + userType + ", typeCode=" + typeCode + ", transactions=" + transactions + '}';
    }
}
