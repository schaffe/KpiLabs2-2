package ua.kpi.dzidzoiev.booking.controller.dao;

import ua.kpi.dzidzoiev.booking.model.City;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by midnight coder on 17-Mar-15.
 */
public class JpaCityDaoImpl implements CityDao {

    EntityManager entityManager;

    public void init(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public City get(Integer id) {
        return entityManager.find(City.class, id);
    }

    @Override
    public List<City> getAll() {
        return entityManager.createQuery("from City ", City.class).getResultList();
    }

    @Override
    public void create(City city) {
        entityManager.persist(city);
    }

    @Override
    public void update(City city) {
        entityManager.merge(city);
    }

    @Override
    public void delete(City city) {
        entityManager.merge(city);
        entityManager.remove(city);
    }
}
