/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.mbeans;

import hotel.jsf.util.JsfUtil;
import hotel.repository.UserRepository;
import hotel.repository.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author maclee
 */
@ManagedBean(name = "userManageBean")
@SessionScoped
public class UserManageBean {

    @EJB
    UserRepository userRepository;

    private User user;
    private User search;
    private User selected;
    private User userProfile;
    private List<User> userList;
    private int roleId;
    private int totalUsers = 0;
    private boolean selectOne = true;
    public static final String CURRENTURL = "customer.xhtml";

    public UserManageBean() {
        user = new User();
        search = new User();
        userProfile = new User();
        userList = new ArrayList<>();
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSearch() {
        return search;
    }

    public void setSearch(User search) {
        this.search = search;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public User getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(User userProfile) {
        this.userProfile = userProfile;
    }

    public boolean isLogin() {
        String p = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (p != null) {
            return true;
        }
        return false;
    }
    
    public boolean isAdmin() throws Exception {
        String p = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (p != null && p.equals("admin")) {
            return true;
        }
        return false;
    }
    
    public int getTotalUsers() {
        if (this.getAllUser() != null) {
            this.totalUsers = this.getAllUser().size();
        } 
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public boolean isSelectOne() {
        return selectOne;
    }

    public void setSelectOne(boolean selectOne) {
        this.selectOne = selectOne;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getAllUser() {
        try {

            this.userList = userRepository.getAllUsers();
            return this.userList;
        } catch (Exception ex) {
            Logger.getLogger(UserManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addUser() throws Exception {
        //this.user.setRole(getRoleById(this.roleId));
        //this.user.setPassword(user.getPassword());
        userRepository.addUser(this.user);
        FacesMessages.info("Added successful!", "Added successful!");
        //JsfUtil.redirect(CURRENTURL);
    }

    public void removeUser() throws Exception {
        Logger.getLogger(UserManageBean.class.getName()).log(Level.SEVERE, null, roleId);
        userRepository.removeUser(this.selected.getUserId());
        this.selectOne = true;
        FacesMessages.info("Delete successful!", "Added successful!");
        // JsfUtil.redirect(CURRENTURL);
    }

    public void editUser() throws Exception {
        //this.selected.setPassword(user.getPassword());
        userRepository.editUser(selected);
        this.selectOne = true;
        JsfUtil.redirect(CURRENTURL);
    }

    public void onSelect(User user) {
        System.out.println("OnSelect:" + user);
        if (null != user) {
            this.selected = user;
            this.selectOne = false;
        }
    }

    public void onDeselect(User user) {
        System.out.println("OnDeselect:" + user);
        if (null != user) {
            this.selected = null;
            this.selectOne = true;
        }
    }

    public User getProfile() throws Exception {
        String p = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (p != null) {
            userProfile = userRepository.searchUserByUserName(p);
            return userProfile;
        }
        return null;
    }

    public void validateEmail(FacesContext context, UIComponent component,
            Object value) throws ValidatorException, Exception {
        Pattern mask = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        String emailField = (String) value;
        Matcher matcher = mask.matcher(emailField);
        if (!matcher.matches()) {
            JsfUtil.invalideMessage("Invalid E-mail format.");
        }
        //if (checkEmailExist(emailField)) {
        //    JsfUtil.invalideMessage("E-mail exist！");
        // }
    }

    public boolean checkEmailExist(String email) throws Exception {
        for (User u : userList) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<User> searchbyMulitColumn() throws Exception {
        if (search != null) {
            userList = userRepository.searchUserByMultipleColunm(search);
        }
        return userList;
    }

    public void clear() throws IOException {
        search.setUserId(0);
        search.setFirstName("");
        search.setLastName("");
        search.setEmail("");
        search.setUserType("");
        search.setPostCode("");

        JsfUtil.redirect(CURRENTURL);
    }

    public List<User> displayUsers() throws Exception {
        if (search == null || search.getUserId() == 0) {
            this.getAllUser();
            if (search.getFirstName() != null || !search.getFirstName().equals("")
                    || search.getLastName() != null || !search.getLastName().equals("")
                    || search.getEmail() != null || !search.getEmail().equals("")
                    || search.getPostCode() != null || !search.getPostCode().equals("")
                    || search.getUserType() != null || !search.getUserType().equals("")) {
                this.searchbyMulitColumn();
            }
        } else {
            this.searchbyMulitColumn();
        }
        return this.userList;
    }

    public void back() throws IOException {
        selectOne = true;
        JsfUtil.redirect("customer.xhtml");

    }

    public void register() throws Exception {
        this.user.setUserType("customer");
        userRepository.addUser(this.user);
        //FacesMessages.info("addUserForm:addedinfo", "Added successful!");
        JsfUtil.redirect("/Hotel/faces/login.xhtml");
    }

    public void editProfile() throws Exception {
        //this.selected.setPassword(user.getPassword());
        userRepository.editUser(userProfile);
        FacesMessages.info("Update successful!", "Update successful!");
        //JsfUtil.redirect("profile.xhtml");
    }
}
