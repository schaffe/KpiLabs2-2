package ua.kpi.dzidzoiev.booking.controller.dao;

import ua.kpi.dzidzoiev.booking.model.City;
import ua.kpi.dzidzoiev.booking.model.City_;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by midnight coder on 17-Mar-15.
 */
@Stateless
public class JpaCityDaoImpl implements CityDao {

    @PersistenceContext(unitName = "Booking")
    EntityManager em;

    @Override
    public City get(Integer id) {
        return em.find(City.class, id);
    }

    @Override
    public List<City> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<City> criteria = cb.createQuery(City.class);
        Root<City> city = criteria.from(City.class);
        criteria.select(city).orderBy(cb.asc(city.get(City_.name)));
        return em.createQuery(criteria).getResultList();    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(City city) {
        em.persist(city);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(City city) {
        em.merge(city);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(City city) {
        em.remove(em.contains(city) ? city : em.merge(city));
    }
}
