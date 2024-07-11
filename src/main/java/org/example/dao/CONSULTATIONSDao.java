package org.example.dao;

import javassist.CodeConverter;
import org.example.db.MCPConnection;
import org.example.dto.*;
import org.example.helpers.MCPConstants;
import org.example.models.CONSULTATIONS;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CONSULTATIONSDao {
    //Patient should be able to request consultation from a selected doctor
    private static final String insert_req_consultaion = "insert into CONSULTATIONS (doctor_id,patient_id,status, diagnosis, rate,request_time,consultation_time ) " + "values (?,?,'" + MCPConstants.consultation_status_pending + "', '', 0, ?, '')";
    // Doctor should be able to check all pending consultation requests
    private static final String select_pending_consultaion = "select * from CONSULTATIONS where doctor_id = ? " + "AND status = '" + MCPConstants.consultation_status_pending + "'";


    //Patient can check a consultation result
    private static final String select_consultation_result = " select * from CONSULTATIONS where patient_id = ? ";


    private static final String select_consultation_ID = " select * from CONSULTATIONS where consultation_id = ? ";


    private static final String select_consultation_result_Patient = " select * from CONSULTATIONS where patient_id = ? AND consultation_id =? ";

    // Doctor should be able to give consultation to a pending request &  Doctor should be able to record a patient’s diagnosis
    private static final String update_diagnosis_consultaion = "update CONSULTATIONS set diagnosis = ?,consultation_time =?, status = '" + MCPConstants.consultation_status_closed + "' " + "where consultation_id  = ?";
    //Patient should be able to rate a doctor
    private static final String update_rate_consultation = "update CONSULTATIONS set rate = ? where consultation_id  = ?";
    //Search by doctor rate
    private static final String SEARCH_DOCTOR_RATE = "SELECT DISTINCT doctor_id FROM CONSULTATIONS WHERE rate = ?";


    private static final String selectAllconsultaions = " select * from CONSULTATIONS ";


    public void insert_req_consultaion(CONSULTATIONSDto c) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(insert_req_consultaion);
        st.setInt(1, c.getDoctor_id());
        st.setInt(2, c.getPatient_id());
        st.setString(3, c.getRequest_time().toString());
        st.executeUpdate();
        conn.close();
    }


    public ArrayList<RateDto> search_doctor_rate(int rate) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SEARCH_DOCTOR_RATE);
        st.setInt(1, rate);
        ResultSet rs = st.executeQuery();
        ArrayList<RateDto> rateDtos = new ArrayList<>();
        while (rs.next()) {
            rateDtos.add(new RateDto(rs));
        }
        return rateDtos;
    }




    public ArrayList<CONSULTATIONSDto> selectAllconsultaions(CONSULTATIONSFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st;
        if (filter.getDoctor_id() != null && filter.getStatus() != null) {
            st = conn.prepareStatement(select_pending_consultaion);
            st.setInt(1, filter.getDoctor_id());
//            st.setString(2, filter.getStatus());
        } else if (filter.getPatient_id() != null && filter.getConsultation_id()!=null) {
            st = conn.prepareStatement(select_consultation_result_Patient);
            st.setInt(1, filter.getPatient_id());
            st.setInt(2, filter.getConsultation_id());
        }  else if (filter.getPatient_id() != null) {
            st = conn.prepareStatement(select_consultation_result);
            st.setInt(1, filter.getPatient_id());
        } else if (filter.getConsultation_id()!= null) {
            st = conn.prepareStatement(select_consultation_ID);
            st.setInt(1, filter.getConsultation_id());
        }else {
            st = conn.prepareStatement(selectAllconsultaions);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<CONSULTATIONSDto> CON = new ArrayList<>();
        while (rs.next()) {
            CON.add(new CONSULTATIONSDto(rs));
        }
        return CON;
    }

    public void update_diagnosis_consultaion(CONSULTATIONS_update_diagnosisDto C) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(update_diagnosis_consultaion);
        st.setString(1, C.getDiagnosis());
        LocalDateTime rightNow = LocalDateTime.now();
        st.setString(2, rightNow.toString());
        st.setInt(3, C.getConsultation_id());
        st.executeUpdate();
        conn.close();
    }

    public void update_rate(CONSULTATIONS_update_rateDto C) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(update_rate_consultation);
        st.setInt(1, C.getRate());
        st.setInt(2, C.getConsultation_id());
        st.executeUpdate();
        conn.close();
    }


}
