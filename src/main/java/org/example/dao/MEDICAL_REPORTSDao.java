package org.example.dao;
import org.example.db.MCPConnection;
import org.example.models.DOCTORS;
import org.example.models.MEDICAL_REPORTS;
import org.example.models.PATIENTS;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MEDICAL_REPORTSDao {
    //Doctor should be able to search patients’ medical records
    private static final String select_medical_history = "select * from MEDICAL_REPORTS where patient_id = ?";
    private static final String insert_medical_report = "insert into MEDICAL_REPORTS (patient_id,details,report_date ) values (?,?,?)";


//    private static final String INSERT_MEDICAL_REPORT = "INSERT INTO MEDICAL_REPORTS (patient_id, details, report_date) " +
//            "SELECT patient_id, " +
//            "CONCAT(
//            "GROUP_CONCAT(CONCAT(diagnosis, consultation_id, consultation_time) SEPARATOR ', ')) AS details, " +
//            "CURRENT_DATE() AS report_date " +
//            "FROM CONSULTATIONS " +
//            "WHERE patient_id = ?";
//
//
//



    public MEDICAL_REPORTS select_medical_history(int patient_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(select_medical_history);
        st.setInt(1, patient_id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new MEDICAL_REPORTS(rs);
        } else {
            return null;
        }
    }
    public void insert_medical_report(MEDICAL_REPORTS MED) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(insert_medical_report);
        st.setInt(1,MED.getPatient_id());
        st.setString(2,MED.getDetails());
        st.setString(3,MED.getReport_date().toString());
        st.executeUpdate();
        conn.close();
    }
//
//    public void insert_medical_report(MEDICAL_REPORTS MED) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(INSERT_MEDICAL_REPORT);
//        st.setInt(1,MED.getPatient_id());
//        st.executeUpdate();
//        conn.close();
//    }


}
