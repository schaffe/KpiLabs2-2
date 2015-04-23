package ua.kpi.dzidzoiev.booking.data;

import ua.kpi.dzidzoiev.booking.model.City;
import ua.kpi.dzidzoiev.booking.model.CityPhoto;
import ua.kpi.dzidzoiev.booking.model.CityPhoto_;
import ua.kpi.dzidzoiev.booking.model.City_;

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

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CityPhoto> criteria = cb.createQuery(CityPhoto.class);
        Root<CityPhoto> cityPhoto = criteria.from(CityPhoto.class);
        criteria.select(cityPhoto).where(cb.and(cb.like(cityPhoto.get(CityPhoto_.url),url),cb.equal(cityPhoto.get(CityPhoto_.city),city)));

        List<CityPhoto> result = em.createQuery(criteria).getResultList();
        CityPhoto photo = null;
        if(result.isEmpty()) {
            photo = new CityPhoto();
        } else {
            photo = result.get(0);
        }

        if(photo == null) {
            photo = new CityPhoto();
        }

        photo.setUrl(url);

        if(photo.getCity() == null) {
            photo.setCity(city);
        }

        return photo;
    }

    public CityPhoto getPhotoByCityId(int cityId) {
        City city = em.find(City.class, cityId);
        return city.getPhoto();
    }

}
