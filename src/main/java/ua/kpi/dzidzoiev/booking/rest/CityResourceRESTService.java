package ua.kpi.dzidzoiev.booking.rest;/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import dzidzoiev.labs.data.MemberRepository;
import dzidzoiev.labs.model.Member;
import dzidzoiev.labs.service.MemberRegistration;
import ua.kpi.dzidzoiev.booking.controller.dao.CityDao;
import ua.kpi.dzidzoiev.booking.model.City;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;


/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/city")
@RequestScoped
public class CityResourceRESTService {
    @Inject
    private Logger log;

    @Inject
    private CityDao dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<City> listAllMembers() {
        return dao.getAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public City lookupMemberById(@PathParam("id") int id) {
        City city = dao.get(id);
        if (city == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return city;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public City createCity(City city) {
        dao.create(city);
        return city;
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCity(City city) {
        Response.ResponseBuilder builder = null;
        dao.update(city);
        builder = Response.ok();
        return builder.build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteCity(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        City city = dao.get(id);
        if (city == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        dao.delete(city);
        builder = Response.ok();
        return builder.build();
    }
}
