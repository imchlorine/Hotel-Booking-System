/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.mbeans;

import hotel.jsf.util.JsfUtil;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author maclee
 */
@ManagedBean(name = "loginManageBean")
@ViewScoped
public class LoginManageBean {

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        JsfUtil.redirect("/Hotel/faces/login.xhtml");
    }

}
