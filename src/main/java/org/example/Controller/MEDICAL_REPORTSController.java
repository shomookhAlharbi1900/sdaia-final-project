package org.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.MEDICAL_REPORTSDao;
import org.example.dao.SCHEDULESDao;
import org.example.models.DOCTORS;
import org.example.models.MEDICAL_REPORTS;
import org.example.models.SCHEDULES;

@Path("/MEDICAL_REPORTS")
public class MEDICAL_REPORTSController {
    MEDICAL_REPORTSDao dao = new MEDICAL_REPORTSDao();
    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insert_medical_report(MEDICAL_REPORTS MED) {
        System.out.println(MED);
        try {
            dao.insert_medical_report(MED);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{patient_id}")
    public MEDICAL_REPORTS select_medical_history(@PathParam("patient_id") int patient_id) {
        try {
            return dao.select_medical_history(patient_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}