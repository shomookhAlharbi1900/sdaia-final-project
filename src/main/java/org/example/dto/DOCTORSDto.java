package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DOCTORSDto {
    private int  doctor_id;
    private String doctor_name;
    private String specialty ;

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

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



    public DOCTORSDto(int doctor_id, String doctor_name, String specialty, int rate, boolean is_available) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.specialty = specialty;
    }

    public DOCTORSDto() {
    }


    @Override
    public String toString() {
        return "DOCTORSDto{" +
                "doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }

    public DOCTORSDto(ResultSet rs) throws SQLException {
        doctor_id= rs.getInt("doctor_id");
        doctor_name=rs.getString("doctor_name");
        specialty = rs.getString("specialty");
    }
}
