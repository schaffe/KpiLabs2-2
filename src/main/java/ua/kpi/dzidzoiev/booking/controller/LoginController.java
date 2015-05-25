package ua.kpi.dzidzoiev.booking.controller;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Model
@Named(value = "login")
public class LoginController {

    public static final String ADMIN_ROLE = "admin-role";
    public static final String USER_ROLE = "user_role";
    @Inject
    private FacesContext facesContext;

    /**
     * @return Principal of the logged-in user
     */
    private Principal getLoggedInUser()
    {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().
                        getExternalContext().getRequest();
        return request.getUserPrincipal();
    }

    /**
     * Property
     * @return true if user is logged in, false otherwise
     */
    public boolean isUserNotLogin()
    {
        return getLoggedInUser() == null;
    }

    /**
     * Property
     * @return Username of the logged-in user
     */
    public String getLoginUserName()
    {
        Principal loginUser = getLoggedInUser();
        if (loginUser != null)
        {
            return loginUser.getName();
        }
        return "Not logged in";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole(ADMIN_ROLE);
    }

}
