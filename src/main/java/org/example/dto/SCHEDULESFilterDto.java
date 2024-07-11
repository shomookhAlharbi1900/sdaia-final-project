package org.example.dto;

import jakarta.ws.rs.QueryParam;

import java.time.LocalDateTime;

public class SCHEDULESFilterDto {
  @QueryParam ("doctor_id") Integer doctor_id;
    @QueryParam ("is_available") Boolean is_available;

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }
}