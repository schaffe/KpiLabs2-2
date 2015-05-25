package ua.kpi.dzidzoiev.booking.controller;

import ua.kpi.dzidzoiev.booking.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by midnight coder on 24-May-15.
 */
@Stateless
public class UserService {

    @PersistenceContext(unitName = "Security")
    EntityManager em;

    public User find(String username, String password) {
        User u = em.find(User.class, username);
        if(u.getPassword().equals(password)) return u;
        return null;
    }

    public User find(String name) {
        return em.find(User.class, name);

    }
}
