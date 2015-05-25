package ua.kpi.dzidzoiev.booking.data;

import ua.kpi.dzidzoiev.booking.controller.dao.CityDao;
import ua.kpi.dzidzoiev.booking.model.City;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class CityRepository implements Serializable{

    @Inject
    CityDao dao;

    public List<City> getAll() {
        return dao.getAll();
    }

    public City findById(Integer id) {
        return dao.get(id);
    }

    public void delete(City c) {
        dao.delete(c);
    }

    public void add(City c) {
        dao.create(c);
    }

    public void update(City c) {
        dao.update(c);
    }
}
