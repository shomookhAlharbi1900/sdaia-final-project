package org.example.Controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.CONSULTATIONSDao;
import org.example.dao.PATIENTSDao;
import org.example.dto.CONSULTATIONSDto;
import org.example.dto.CONSULTATIONSFilterDto;
import org.example.dto.CONSULTATIONS_update_diagnosisDto;
import org.example.dto.CONSULTATIONS_update_rateDto;
import org.example.models.CONSULTATIONS;
import org.example.models.DOCTORS;

import java.util.ArrayList;

@Path("/CONSULTATIONS")
public class CONSULTATIONSController {
    CONSULTATIONSDao dao = new CONSULTATIONSDao();


    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response selectAllconsultaions(
            @BeanParam CONSULTATIONSFilterDto filter) {
        try {
            GenericEntity<ArrayList<CONSULTATIONSDto>> CON = new GenericEntity<ArrayList<CONSULTATIONSDto>>(dao.selectAllconsultaions(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(CON)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(CON)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(CON, MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin", "*")
                    .header(("Access-Control-Allow-Methods"), "GET, POST, PUT")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insert_req_consultaion(CONSULTATIONSDto CON) {
        System.out.println(CON);
        try {
            dao.insert_req_consultaion(CON);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path(value = "/rate")
    public Response update_rate(CONSULTATIONS_update_rateDto CON) {
        try {
            dao.update_rate(CON);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path(value = "/diagnosis")
    public Response update_diagnosis_consultaion(CONSULTATIONS_update_diagnosisDto CON) {
        try {

            dao.update_diagnosis_consultaion(CON);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}