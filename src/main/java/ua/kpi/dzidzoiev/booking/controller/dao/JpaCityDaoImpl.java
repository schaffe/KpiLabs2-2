package ua.kpi.dzidzoiev.booking.controller.dao;

import dzidzoiev.labs.model.Member;
import ua.kpi.dzidzoiev.booking.model.City;
import ua.kpi.dzidzoiev.booking.model.City_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by midnight coder on 17-Mar-15.
 */
public class JpaCityDaoImpl implements CityDao {
    @Inject
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> criteria = cb.createQuery(City.class);
        Root<City> city = criteria.from(City.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
         criteria.select(city).orderBy(cb.asc(city.get(City_.name)));
//        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return entityManager.createQuery(criteria).getResultList();    }

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
