package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CONSULTATIONS_update_diagnosisDto {
    private int  consultation_id ;
    private String status ;
    private String diagnosis ;
    private LocalDateTime consultation_time ;

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
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



    public LocalDateTime getConsultation_time() {
        return consultation_time;
    }

    public void setConsultation_time(LocalDateTime consultation_time) {
        this.consultation_time = consultation_time;
    }

    public CONSULTATIONS_update_diagnosisDto(int consultation_id, int doctor_id, int patient_id, String status, String diagnosis, int rate, LocalDateTime request_time, LocalDateTime consultation_time) {
        this.consultation_id = consultation_id;
        this.status = status;
        this.diagnosis = diagnosis;
        this.consultation_time = consultation_time;
    }

    public CONSULTATIONS_update_diagnosisDto() {
    }


    @Override
    public String toString() {
        return "CONSULTATIONS_update_diagnosisDto{" +
                "consultation_id=" + consultation_id +
                ", status='" + status + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", consultation_time=" + consultation_time +
                '}';
    }

    public CONSULTATIONS_update_diagnosisDto(ResultSet rs) throws SQLException {
        consultation_id= rs.getInt("consultation_id");
        status=rs.getString("status");
        diagnosis=rs.getString("diagnosis");
        if (rs.getString("consultation_time").equals("")) {
            consultation_time = null;
        }
        else {
            consultation_time = LocalDateTime.parse(rs.getString("consultation_time"));
        }

    }
}
