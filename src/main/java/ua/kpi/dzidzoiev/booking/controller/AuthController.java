package ua.kpi.dzidzoiev.booking.controller;

import ua.kpi.dzidzoiev.booking.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

@Named(value = "auth")
@SessionScoped
public class AuthController implements Serializable {

    private String username;
    private String password;
    private String originalURL;
    User user;

    @EJB
    private UserService userService;

    public User getUser() {
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userService.find(principal.getName()); // Find User by j_username.
            }
        }
        return user;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }


//    @PostConstruct
//    public void init() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
//
//        if (originalURL == null) {
//            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
//        } else {
//            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
//
//            if (originalQuery != null) {
//                originalURL += "?" + originalQuery;
//            }
//        }
//    }
//
//    public void login() throws IOException, ServletException {
//        FacesContext context = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = context.getExternalContext();
//        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//
//        try {
//            request.login(username, password);
//            User user = userService.find(username, password);
//            externalContext.getSessionMap().put("user", user);
//            externalContext.redirect(originalURL);
//        } catch (ServletException e) {
//            // Handle unknown username/password in request.login().
//            context.addMessage(null, new FacesMessage("Unknown login"));
//        }
//    }
//
//    public void logout() throws IOException {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        externalContext.invalidateSession();
//        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
}