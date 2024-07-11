package org.example.dto;
//
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MEDICAL_REPORTS_patientDto {
    private int patient_id ;
    private  String patient_name;
    private  String patient_phone ;
    private LocalDate birth_date ;
    private String diagnosis ;

    public MEDICAL_REPORTS_patientDto(int patient_id, String patient_name, String patient_phone, LocalDate birth_date, String diagnosis) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_phone = patient_phone;
        this.birth_date = birth_date;
        this.diagnosis = diagnosis;
    }

    public MEDICAL_REPORTS_patientDto() {
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public MEDICAL_REPORTS_patientDto(ResultSet rs) throws SQLException {

        patient_id = rs.getInt("patient_id");
        patient_name=rs.getString("patient_name");
        patient_phone=rs.getString("patient_phone");
        birth_date = LocalDate.parse(rs.getString("birth_date"));
        diagnosis=rs.getString("diagnosis");

    }


    @Override
    public String toString() {
        return "MEDICAL_REPORTS_patientDto{" +
                "patient_id=" + patient_id +
                ", patient_name='" + patient_name + '\'' +
                ", patient_phone='" + patient_phone + '\'' +
                ", birth_date=" + birth_date +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
