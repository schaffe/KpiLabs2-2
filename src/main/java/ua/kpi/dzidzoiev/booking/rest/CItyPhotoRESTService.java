package ua.kpi.dzidzoiev.booking.rest;

import ua.kpi.dzidzoiev.booking.data.CityPhotoRepository;
import ua.kpi.dzidzoiev.booking.model.City;
import ua.kpi.dzidzoiev.booking.model.CityPhoto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by midnight coder on 23-Apr-15.
 */
@Path("/city/{id:[0-9][0-9]*}/photo")
@RequestScoped
public class CItyPhotoRESTService {

    @Inject
    CityPhotoRepository repository;

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public CityPhoto create(@PathParam("id")int cityId, String url) {
        return repository.saveOrUpdatePhoto(cityId, url);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CityPhoto getPhoto(@PathParam("id")int cityId) {
        return repository.getPhotoByCityId(cityId);
    }

    @DELETE
    public void delete(@PathParam("id") int cityId) {
        repository.delete(cityId);
    }

}
