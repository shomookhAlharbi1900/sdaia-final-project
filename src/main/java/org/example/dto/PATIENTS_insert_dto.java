package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PATIENTS_insert_dto {
    private String patient_name ;
    private String patient_email ;
    private String patient_password ;
    private String patient_phone ;
    private LocalDate birth_date;

    public PATIENTS_insert_dto(String patient_name, String patient_email, String patient_password, String patient_phone, LocalDate birth_date) {
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_password = patient_password;
        this.patient_phone = patient_phone;
        this.birth_date = birth_date;
    }

    public PATIENTS_insert_dto(ResultSet rs) throws SQLException {
        patient_name=rs.getString("patient_name");
        patient_email=rs.getString("patient_email");
        patient_password=rs.getString("patient_password");
        patient_phone=rs.getString("patient_phone");
        birth_date = LocalDate.parse(rs.getString("birth_date"));
    }

    public PATIENTS_insert_dto() {
    }


    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_password() {
        return patient_password;
    }

    public void setPatient_password(String patient_password) {
        this.patient_password = patient_password;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }


    @Override
    public String toString() {
        return "PATIENTS_insert_dto{" +
                "patient_name='" + patient_name + '\'' +
                ", patient_email='" + patient_email + '\'' +
                ", patient_password='" + patient_password + '\'' +
                ", patient_phone='" + patient_phone + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}
