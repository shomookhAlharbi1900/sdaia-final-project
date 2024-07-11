package org.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MEDICAL_REPORTS {
private  int id_report ;
private  int patient_id ;
private  String details ;
private LocalDate report_date ;






    public MEDICAL_REPORTS(int id_report, int patient_id, String details, LocalDate report_date) {
        this.id_report = id_report;
        this.patient_id = patient_id;
        this.details = details;
        this.report_date = report_date;
    }


    public MEDICAL_REPORTS(ResultSet rs) throws SQLException {
        id_report= rs.getInt("id_report");
        patient_id= rs.getInt("patient_id");
        details=rs.getString("details");
        report_date = LocalDate.parse(rs.getString("report_date"));
    }

    public int getId_report() {
        return id_report;
    }

    public void setId_report(int id_report) {
        this.id_report = id_report;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getReport_date() {
        return report_date;
    }

    public void setReport_date(LocalDate report_date) {
        this.report_date = report_date;
    }

    @Override
    public String toString() {
        return "MEDICAL_REPORTS{" +
                "id_report=" + id_report +
                ", patient_id=" + patient_id +
                ", details='" + details + '\'' +
                ", report_date=" + report_date +
                '}';
    }
}
