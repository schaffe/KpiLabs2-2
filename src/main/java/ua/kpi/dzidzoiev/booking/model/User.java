package ua.kpi.dzidzoiev.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by midnight coder on 24-May-15.
 */
@Entity(name = "principles")
public class User implements Serializable {
    @Id
    @Column(name = "principal_id")
    String name;
    @Column
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
