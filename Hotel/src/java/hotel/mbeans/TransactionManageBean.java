/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.mbeans;

import hotel.jsf.util.JsfUtil;
import hotel.repository.TransactionRepository;
import hotel.repository.UserRepository;
import hotel.repository.entities.Room;
import hotel.repository.entities.Transaction;
import hotel.repository.entities.TransactionType;
import hotel.repository.entities.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author maclee
 */
@ManagedBean(name = "transactionManageBean")
@SessionScoped
public class TransactionManageBean implements Serializable {

    @EJB
    TransactionRepository transactionRepository;
    @EJB
    UserRepository userRepository;

    private List<Transaction> transactions;
    private Transaction transaction;
    private Transaction select;
    private int transNumber = 0;
    private boolean selectOne = true;
    private List<TransactionType> transactionTypes;
    private TransactionType transactionType;
    private int transTypeId;
    private int roomTypeId;
    public static final String CURRENTURL = "transaction.xhtml";

    public TransactionManageBean() throws Exception {
        this.transaction = new Transaction();
        //this.transactionType = new TransactionType();
        this.transactions = new ArrayList<>();
        this.transactionTypes = new ArrayList<>();

    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction getSelect() {
        return select;
    }

    public void setSelect(Transaction select) {
        this.select = select;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public List<TransactionType> getTransactionTypes() throws Exception {
        transactionTypes = transactionRepository.getAllTypes();
        return transactionTypes;
    }

    public int getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(int transTypeId) {
        this.transTypeId = transTypeId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public boolean isSelectOne() {
        return selectOne;
    }

    public void setSelectOne(boolean selectOne) {
        this.selectOne = selectOne;
    }

    public void setTransactionTypes(List<TransactionType> transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public int getTransNumber() {
        if (getAllTransactions() != null) {
            transNumber = getAllTransactions().size();
        }
        return transNumber;
    }

    public void setTransNumber(int transNumber) {
        this.transNumber = transNumber;
    }

    public List<Transaction> getAllTransactions() {
        try {
            this.transactions = transactionRepository.getAllTransactions();
            return this.transactions;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addTransaction() throws Exception {
        setRoomByTypeRandom();
        transaction.setTransType(getranTransactionTypeById(this.transTypeId));
        transaction.setStatus("Paid");
        transaction.setUser(getProfile());
        transactionRepository.addTransection(this.transaction);
        JsfUtil.redirect(CURRENTURL);
    }

    public void deleteTransaction() throws Exception {
        transactionRepository.removeTransaction(this.select.getTransNo());
        this.selectOne = true;
        JsfUtil.redirect(CURRENTURL);
    }

    public TransactionType getranTransactionTypeById(int id) throws Exception {
        TransactionType transactiontype = transactionRepository.searchTransactionTypeById(id);
        return transactiontype;
    }

    public void searchTransactionById(int id) throws Exception {
        this.transactions.clear();
        transactions.add(transactionRepository.searchTransactionById(id));
    }

    public void searchTransactionByType(TransactionType type) throws Exception {
        this.transactions.clear();
        this.transactions.addAll(transactionRepository.searchTransactionByType(type));
    }

    public void searchTransactionByName(String name) throws Exception {
        this.transactions.clear();
        this.transactions.addAll(transactionRepository.searchTransactionByName(name));
    }

    public List<Transaction> displayTransaction() throws Exception {
        int id = this.transaction.getTransNo();
        String name = this.transaction.getTransName();
        int typeId = this.transTypeId;
        TransactionType transType;
        if (typeId < 1) {
            transType = null;
        } else {
            transType = transactionTypes.get(typeId - 1);
        }
        if (id == 0) {
            if (!"".equals(name) && name != null) {
                searchTransactionByName(name);
                return this.transactions;
            }
            if (transType != null) {
                searchTransactionByType(transType);
                return this.transactions;
            }
            getAllTransactions();
            return this.transactions;
        }
        if (id > 0) {
            searchTransactionById(id);
            return this.transactions;
        }
        return null;
    }

    public List<TransactionType> selectTypes() {
        try {
            transactionTypes = transactionRepository.getAllTypes();
            return transactionTypes;
        } catch (Exception ex) {
            Logger.getLogger(TransactionManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void clear() throws IOException {
        JsfUtil.redirect(CURRENTURL);
    }

    public void onSelect(Transaction trans) {
        System.out.println("OnSelect:" + trans);
        if (null != trans) {
            this.select = trans;
            this.selectOne = false;
        }
    }

    public void onDeselect(Transaction trans) {
        System.out.println("OnDeselect:" + trans);
        if (null != trans) {
            this.select = null;
            this.selectOne = true;
        }
    }

    public void setRoomByTypeRandom() throws Exception {
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(transactionRepository.getRoomByType(roomTypeId));
        Random random = new Random();
        int n = random.nextInt(rooms.size());
        this.transaction.setRooms(rooms.get(n));
        //String checkinDate = new SimpleDateFormat("yyyy-MM-dd").format(this.transaction.getStartDate());
        // String chechoutDate = new SimpleDateFormat("yyyy-MM-dd").format(this.transaction.getEndDate());
        // int roomTypeId = this.roomTypeId;
        // JsfUtil.redirect("faces/customer/pay.xhtml?" + "checkinDate=" + checkinDate + "&chechoutDate=" + chechoutDate + "&roomTypeId=" + roomTypeId);
    }

    public User getProfile() throws Exception {
        String p = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (p != null) {
            User u = userRepository.searchUserByUserName(p);
            return u;
        }
        return null;
    }

    public void viewTransactionDetail(Transaction transaction) throws IOException {
        this.select = transaction;
        JsfUtil.redirect("transdetail.xhtml");
    }

    public void cancelTransaction(Transaction transaction) throws Exception {
        transaction.setStatus("Cancel");
        transactionRepository.editTransaction(transaction);
        JsfUtil.redirect(CURRENTURL);

    }

    public List<Transaction> diplayTransactionsByUser() throws Exception {
        List<Transaction> trans = new ArrayList<>();
        trans.addAll(transactionRepository.searchTransactionByUser(getProfile()));
        return trans;
    }

    public void back() throws IOException {
        select = new Transaction();
        JsfUtil.redirect("transaction.xhtml");

    }

}
