package ua.kpi.dzidzoiev.booking.data;

import ua.kpi.dzidzoiev.booking.model.City;
import ua.kpi.dzidzoiev.booking.model.CityPhoto;
import ua.kpi.dzidzoiev.booking.model.CityPhoto_;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by midnight coder on 23-Apr-15.
 */
@Stateless
public class CityPhotoRepository {

    @PersistenceContext(unitName = "Booking")
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public CityPhoto saveOrUpdatePhoto(int cityId, String url) {
        City city = em.find(City.class, cityId);
        CityPhoto photo = city.getPhoto();

        if (photo == null) {
            photo = new CityPhoto();
        }

        photo.setUrl(url);
        photo.setCity(city);

        city.setPhoto(photo);
        em.merge(city);

        return photo;
    }

    public CityPhoto getPhotoByCityId(int cityId) {
        City city = em.find(City.class, cityId);
        return city.getPhoto();
    }

    public void delete(int cityId) {
        City city = em.find(City.class, cityId);
        em.remove(city.getPhoto());
    }
}
