package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class is_availableDto {

    private int  doctor_id ;

    public is_availableDto(ResultSet rs) throws SQLException {

        doctor_id = rs.getInt("doctor_id");
    }
    public is_availableDto(int doctor_id) {
        this.doctor_id = doctor_id;

    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
//
//    public int getPatient_id() {
//        return patient_id;
//    }
//
//    public void setPatient_id(int patient_id) {
//        this.patient_id = patient_id;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getDiagnosis() {
//        return diagnosis;
//    }
//
//    public void setDiagnosis(String diagnosis) {
//        this.diagnosis = diagnosis;
//    }
//
//    public int getRate() {
//        return rate;
//    }
//
//    public void setRate(int rate) {
//        this.rate = rate;
//    }

//    public LocalDateTime getRequest_time() {
//        return request_time;
//    }
//
//    public void setRequest_time(LocalDateTime request_time) {
//        this.request_time = request_time;
//    }
//
//    public LocalDateTime getConsultation_time() {
//        return consultation_time;
//    }
//
//    public void setConsultation_time(LocalDateTime consultation_time) {
//        this.consultation_time = consultation_time;
//    }
//

    public is_availableDto() {
    }

//
//    @Override
//    public String toString() {
//        return "CONSULTATIONSDto{" +
////                "consultation_id=" + consultation_id +
//                " doctor_id=" + doctor_id +
////                ", patient_id=" + patient_id +
////                ", status='" + status + '\'' +
////                ", diagnosis='" + diagnosis + '\'' +
////                " rate=" + rate +
////                ", request_time=" + request_time +
////                ", consultation_time=" + consultation_time +
//                '}';
//    }


    @Override
    public String toString() {
        return "is_availableDto{" +
                "doctor_id=" + doctor_id +
                '}';
    }
}
