package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CONSULTATIONSDto {
    private int  consultation_id ;
    private int  doctor_id ;
    private int  patient_id ;
    private String status ;
    private String diagnosis ;
    private int  rate ;
    private LocalDateTime request_time ;
    private LocalDateTime consultation_time ;

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public LocalDateTime getRequest_time() {
        return request_time;
    }

    public void setRequest_time(LocalDateTime request_time) {
        this.request_time = request_time;
    }

    public LocalDateTime getConsultation_time() {
        return consultation_time;
    }

    public void setConsultation_time(LocalDateTime consultation_time) {
        this.consultation_time = consultation_time;
    }

    public CONSULTATIONSDto(int consultation_id, int doctor_id, int patient_id, String status, String diagnosis, int rate, LocalDateTime request_time, LocalDateTime consultation_time) {
        this.consultation_id = consultation_id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.status = status;
        this.diagnosis = diagnosis;
        this.rate = rate;
        this.request_time = request_time;
        this.consultation_time = consultation_time;
    }

    public CONSULTATIONSDto() {
    }


    @Override
    public String toString() {
        return "CONSULTATIONSDto{" +
                "consultation_id=" + consultation_id +
                ", doctor_id=" + doctor_id +
                ", patient_id=" + patient_id +
                ", status='" + status + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", rate=" + rate +
                ", request_time=" + request_time +
                ", consultation_time=" + consultation_time +
                '}';
    }


    public CONSULTATIONSDto(ResultSet rs) throws SQLException {
        consultation_id= rs.getInt("consultation_id");
        doctor_id=rs.getInt("doctor_id");
        patient_id=rs.getInt("patient_id");
        status=rs.getString("status");
        diagnosis=rs.getString("diagnosis");
        rate= rs.getInt("rate");
        request_time = LocalDateTime.parse(rs.getString("request_time"));
        if (rs.getString("consultation_time").equals("")) {
            consultation_time = null;
        }
        else {
            consultation_time = LocalDateTime.parse(rs.getString("consultation_time"));
        }

    }
}
