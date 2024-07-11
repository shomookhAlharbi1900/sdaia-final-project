package org.example.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.PATIENTSDao;
import org.example.dto.*;
import org.example.models.DOCTORS;
import org.example.models.PATIENTS;

import java.util.ArrayList;

@Path("/PATIENTS")
public class PATIENTSController {
    PATIENTSDao dao = new PATIENTSDao();

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insertPatient(PATIENTS_insert_dto PAT) {
        System.out.println(PAT);
        try {
            dao.insertPatient(PAT);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
    public Response selectAllPatients() {
        try {
            GenericEntity<ArrayList<PATIENTSDto>> PAT = new GenericEntity<ArrayList<PATIENTSDto>>(dao.selectAllPatients()) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(PAT)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(PAT)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(PAT, MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin", "*")
                    .header(("Access-Control-Allow-Methods"), "GET, POST, PUT")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @PUT
//    @Path("{patient_id}")
//    public Response updatePatient(@PathParam("patient_id") int patient_id, PATIENTS PAT) {
//        try {
//            PAT.setPatient_id(patient_id);
//            dao.updatePatient(PAT);
//            return Response.status(Response.Status.NO_CONTENT).build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GET
    @Path("{patient_id}")
    public PATIENTSDto selectPatient(@PathParam("patient_id") int patient_id) {
        try {
            return dao.selectPatient(patient_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//    @GET
//    @Path("/medical-report/{patient_id}")
//    public MEDICAL_REPORTS_patientDto medical_report_pat(@PathParam("patient_id") int patient_id) {
//        try {
//            return dao.medical_report_pat(patient_id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//

    @GET
    @Path("/medical-report/{patient_id}")
    public Response getMedicalReport(@PathParam("patient_id") int patient_id) {
        ArrayList<MEDICAL_REPORTS_patientDto> medical_report = new ArrayList<>();
        try {
            ArrayList<MEDICAL_REPORTS_patientDto> me = dao.medical_report_pat(patient_id);
            for (MEDICAL_REPORTS_patientDto medicll : me) {
                medical_report.add(medicll);

            }
            return Response.ok(medical_report)
                    .header("Access-Control-Allow-Origin", "*")
                    .header(("Access-Control-Allow-Methods"), "GET, POST, PUT")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }









//

//    @GET
//    @Path("/medical-report/{patient_id}")
//    public ArrayList<MEDICAL_REPORTS_patientDto> medical_report_pat(@PathParam("patient_id") int patient_id) {
//        try {
//            return dao.medical_report_pat(patient_id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }



}