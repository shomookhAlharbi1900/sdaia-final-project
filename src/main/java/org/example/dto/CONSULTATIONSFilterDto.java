package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class CONSULTATIONSFilterDto {
    @QueryParam("doctor_id") Integer doctor_id ;
    @QueryParam("status")  String status ;
    @QueryParam("patient_id")  Integer patient_id ;
    @QueryParam("consultation_id")  Integer consultation_id ;

    public Integer getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(Integer consultation_id) {
        this.consultation_id = consultation_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
}
