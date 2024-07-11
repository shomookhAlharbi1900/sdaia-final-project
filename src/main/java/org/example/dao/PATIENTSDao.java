package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.MEDICAL_REPORTS_patientDto;
import org.example.dto.PATIENTSDto;
import org.example.dto.PATIENTS_insert_dto;
import org.example.dto.is_availableDto;
import org.example.models.DOCTORS;
import org.example.models.PATIENTS;

import java.sql.*;
import java.util.ArrayList;


public class PATIENTSDao {

    //Patient registration
    private static final String insertPatient = "insert into PATIENTS (patient_name,patient_email,patient_password,patient_phone,birth_date ) values (?,?,?,?,?)";
    private static final String selectPatient = "select * from PATIENTS where patient_id = ?";

    private static final String selectAllPatients = " select * from PATIENTS ";
//    private static final String updatePatient = "update PATIENTS set patient_name = ?, patient_email = ? ,patient_password = ?, patient_phone = ?,birth_date=? where patient_id  = ?";


    // Patient login
private static final String selectLogin_Patient = "SELECT * FROM PATIENTS WHERE patient_email = ? AND patient_password = ?";


//Doctor should be able to search patients’ medical records AND • Patient can request a medical history report for all previously recorded diagnosis
    private static final String medical_report_pat = "SELECT p.patient_id ,p.patient_name, p.patient_phone, p.birth_date, c.diagnosis, c.consultation_time FROM PATIENTS p JOIN CONSULTATIONS c ON p.patient_id = c.patient_id WHERE p.patient_id = ?";

//    public MEDICAL_REPORTS_patientDto medical_report_pat(int patient_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn= MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(medical_report_pat);
//        st.setInt(1, patient_id);
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            return new MEDICAL_REPORTS_patientDto(rs);
//        } else {
//            return null;
//        }
//    }


    public ArrayList<MEDICAL_REPORTS_patientDto> medical_report_pat(int patient_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(medical_report_pat);
        st.setInt(1, patient_id);
        ResultSet rs = st.executeQuery();
        ArrayList<MEDICAL_REPORTS_patientDto> medical_report = new ArrayList<>();
        while (rs.next()) {
            medical_report.add(new MEDICAL_REPORTS_patientDto(rs));
        }
        return medical_report;
    }



//    public ArrayList<MEDICAL_REPORTS_patientDto> medical_report_pat(int patient_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn= MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(medical_report_pat);
//        ResultSet rs = st.executeQuery();
//        ArrayList<MEDICAL_REPORTS_patientDto> PAT = new ArrayList<>();
//        while (rs.next()) {
//            PAT.add(new MEDICAL_REPORTS_patientDto(rs));
//        }
//        return PAT;
//    }













    public void insertPatient(PATIENTS_insert_dto p) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(insertPatient);
        st.setString(1,p.getPatient_name());
        st.setString(2,p.getPatient_email());
        st.setString(3,p.getPatient_password());
        st.setString(4,p.getPatient_phone());
        st.setString(5,p.getBirth_date().toString());
        st.executeUpdate();
        conn.close();
    }



    public ArrayList<PATIENTSDto> selectAllPatients() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(selectAllPatients);
        ResultSet rs = st.executeQuery();
        ArrayList<PATIENTSDto> PAT = new ArrayList<>();
        while (rs.next()) {
            PAT.add(new PATIENTSDto(rs));
        }
        return PAT;
    }
    public PATIENTSDto selectPatient(int patient_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(selectPatient);
        st.setInt(1, patient_id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new PATIENTSDto(rs);
        } else {
            return null;
        }
    }

//    public void updatePatient(PATIENTS PAT) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn= MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(updatePatient);
//        st.setString(1, PAT.getPatient_name());
//        st.setString(2, PAT.getPatient_email());
//        st.setString(3, PAT.getPatient_password());
//        st.setString(4, PAT.getPatient_phone());
//        st.setString(5, PAT.getBirth_date().toString());
//        st.setInt(6, PAT.getPatient_id());
//        st.executeUpdate();
//        conn.close();
//    }

    public PATIENTS selectLogin_Patient(String patient_email ,String patient_password ) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(selectLogin_Patient);
        st.setString(1, patient_email);
        st.setString(2, patient_password);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new PATIENTS(rs);
        }
        else {
            return null;
        }
    }



}
