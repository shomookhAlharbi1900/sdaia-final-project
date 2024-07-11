package org.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DOCTORS {
    private int  doctor_id;
    private String doctor_name;
    private String specialty ;
    private String doctor_email ;
    private String doctor_password ;
    private String doctor_phone ;

    public DOCTORS(int doctor_id, String doctor_name, String specialty, String doctor_email, String doctor_password, String doctor_phone) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.specialty = specialty;
        this.doctor_email = doctor_email;
        this.doctor_password = doctor_password;
        this.doctor_phone = doctor_phone;
    }

    public DOCTORS() {
    }

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

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public String getDoctor_phone() {
        return doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        this.doctor_phone = doctor_phone;
    }
    public DOCTORS(ResultSet rs) throws SQLException {
        doctor_id= rs.getInt("doctor_id");
        doctor_name=rs.getString("doctor_name");
        specialty = rs.getString("specialty");
        doctor_email =rs.getString("doctor_email");
        doctor_password =rs.getString("doctor_password");
        doctor_phone =rs.getString("doctor_phone");
    }
    @Override
    public String toString() {
        return "DOCTORS{" +
                "doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", doctor_email='" + doctor_email + '\'' +
                ", doctor_password='" + doctor_password + '\'' +
                ", doctor_phone='" + doctor_phone + '\'' +
                '}';
    }
}
