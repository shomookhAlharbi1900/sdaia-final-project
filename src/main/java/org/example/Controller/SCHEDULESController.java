package org.example.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.CONSULTATIONSDao;
import org.example.dao.SCHEDULESDao;
import org.example.dto.CONSULTATIONSFilterDto;
import org.example.dto.SCHEDULESDto;
import org.example.dto.SCHEDULESFilterDto;
import org.example.models.CONSULTATIONS;
import org.example.models.PATIENTS;
import org.example.models.SCHEDULES;

import java.util.ArrayList;

@Path("/SCHEDULES")
public class SCHEDULESController {
    SCHEDULESDao dao = new SCHEDULESDao();

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insertSCHEDULES(SCHEDULESDto SECH) {
        System.out.println(SECH);
        try {
            dao.insertSCHEDULES(SECH);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PUT
    @Path("{id}")
    public Response updateSCHEDULES(@PathParam("id") int id, SCHEDULESDto SCH) {
        try {
            SCH.setId(id);
            dao.updateSCHEDULES(SCH);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
    public Response selectAllSCHEDULES( @BeanParam SCHEDULESFilterDto filter) {
        try {
            GenericEntity<ArrayList<SCHEDULESDto>> SECHE = new GenericEntity<ArrayList<SCHEDULESDto>>(dao.selectAllSCHEDULES(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(SECHE)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(SECHE)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(SECHE, MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin", "*")
                    .header(("Access-Control-Allow-Methods"), "GET, POST, PUT")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}