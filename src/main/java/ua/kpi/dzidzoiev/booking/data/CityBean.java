package ua.kpi.dzidzoiev.booking.data;

import dzidzoiev.labs.model.Member;
import ua.kpi.dzidzoiev.booking.controller.dao.CityDao;
import ua.kpi.dzidzoiev.booking.model.City;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by midnight coder on 23-May-15.
 */
@Named(value = "city")
@SessionScoped
public class CityBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private City selected;

    private List<City> cities;

    @Inject
    private CityRepository repository;

    @Inject
    private Event<City> cityEventSrc;

    boolean editMode;

    public boolean isEditMode() {
        return editMode;
    }

    public void reset() {
        setSelected(new City());
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public City getSelected() {
        return selected;
    }

    public void setSelected(City selected) {
        this.selected = selected;
    }

    public List<City> getAll() {
        return cities;
    }

    public void delete(City c) {
        repository.delete(c);
        cityEventSrc.fire(c);
    }

    public void add() {
        repository.add(selected);
        setSelected(new City());
        cityEventSrc.fire(selected);
    }

    public void update() {
        repository.update(selected);
        setSelected(new City());
        cityEventSrc.fire(selected);
    }

    public void onCityListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final City city) {
        retrieveAllCities();
    }

    @PostConstruct
    public void retrieveAllCities() {
        cities = repository.getAll();
    }
}
