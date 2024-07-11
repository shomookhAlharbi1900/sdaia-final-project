package org.example.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.CONSULTATIONSDao;
import org.example.dao.DOCTORSDao;
import org.example.dao.SCHEDULESDao;
//import org.example.dto.DOCTORSDto;
import org.example.dto.*;
import org.example.models.CONSULTATIONS;
import org.example.models.DOCTORS;

import java.util.ArrayList;

@Path("/DOCTORS")
public class DOCTORSController {
    DOCTORSDao dao = new DOCTORSDao();
    CONSULTATIONSDao consDao = new CONSULTATIONSDao();
    SCHEDULESDao schedDao = new SCHEDULESDao();
    @Context  UriInfo uriInfo;
    @Context HttpHeaders headers;
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insertDoctors(DOCTORS_insert_dto doct) {
        System.out.println(doct);
        try {
            dao.insertDoctors(doct);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    @PUT
//    @Path("{doctor_id}")
//    public Response updateDoctors(@PathParam("doctor_id") int doctor_id, DOCTORS DOCT) {
//        try {
//            DOCT.setDoctor_id(doctor_id);
//            dao.updateDoctors(DOCT);
//            return Response.status(Response.Status.NO_CONTENT).build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @GET
//    @Path("{doctor_id}")
//    public DOCTORS selectDoctor(@PathParam("doctor_id") int doctor_id) {
//        try {
//            return dao.selectDoctor(doctor_id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response selectAllDoctors(
            @BeanParam DOCTORSFilterDto filter) {
        try {
            GenericEntity<ArrayList<DOCTORSDto>> doct = new GenericEntity<ArrayList<DOCTORSDto>>(dao.selectAllDoctors(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(doct)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            return Response
                    .ok(doct, MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin", "*")
                    .header(("Access-Control-Allow-Methods"), "GET, POST, PUT")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    public Response getDoctorByRate(@QueryParam("rate") int rate) {

        ArrayList<DOCTORSDto> doctors = new ArrayList<>();
        try {
            ArrayList<RateDto> rateDtos = consDao.search_doctor_rate(rate);

            for (RateDto rateDto : rateDtos) {
                doctors.add(dao.selectDoctor(rateDto.getDoctor_id()));
            }

            return Response.ok(doctors).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("/available")
    public Response getDoctorByis_available() {
        ArrayList<DOCTORSDto> doctors = new ArrayList<>();
        try {
            ArrayList<is_availableDto> is_availableDtos = schedDao.select_SCHEDULE_with_available(true);

            for (is_availableDto is_availableDto : is_availableDtos) {
                doctors.add(dao.selectDoctor(is_availableDto.getDoctor_id()));
            }

            return Response.ok(doctors)
                    .header("Access-Control-Allow-Origin", "*")
                    .header(("Access-Control-Allow-Methods"), "GET, POST, PUT")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
