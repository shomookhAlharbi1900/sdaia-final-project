package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class DOCTORSFilterDto {
    @QueryParam("doctor_name")  String doctor_name ;
    @QueryParam("specialty")  String specialty ;
    @QueryParam("doctor_id") Integer doctor_id ;

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }



}
