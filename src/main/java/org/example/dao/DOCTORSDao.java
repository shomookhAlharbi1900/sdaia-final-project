package org.example.dao;
//import org.example.dto.DOCTORSDto;
import org.example.db.MCPConnection;
import org.example.dto.DOCTORSDto;
import org.example.dto.DOCTORSFilterDto;
import org.example.dto.DOCTORS_insert_dto;
import org.example.models.DOCTORS;

import java.sql.*;
import java.util.ArrayList;

public class DOCTORSDao {
    //Doctor registration
    private static final String insertDoctors = "insert into DOCTORS (doctor_name,specialty,doctor_email,doctor_password,doctor_phone ) values (?,?,?,?,?)";
    //Doctor login
    private static final String selectLogin_doctor = "SELECT * FROM DOCTORS WHERE Doctor_email = ? AND Doctor_password = ?";


    private static final String selectDoctor = "select * from DOCTORS where doctor_id = ?";
    private static final String selectAllDoctors = " select * from DOCTORS ";


    //Search by
    private static final String searchDoctors__name = "select * from DOCTORS where LOWER (doctor_name) = LOWER(?)";
    private static final String searchDoctors_specialty = "select * from DOCTORS where specialty = ?";
    private static final String searchDoctor_ID = "select * from DOCTORS where doctor_id = ?";



//    private static final String updateDoctors = "update DOCTORS set doctor_name = ?, doctor_phone = ? where doctor_id  = ?";



    public void insertDoctors(DOCTORS_insert_dto d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(insertDoctors);
        st.setString(1,d.getDoctor_name());
        st.setString(2,d.getSpecialty());
        st.setString(3,d.getDoctor_email());
        st.setString(4,d.getDoctor_password());
        st.setString(5,d.getDoctor_phone());
        st.executeUpdate();
        conn.close();
    }

    public DOCTORSDto selectDoctor(int doctor_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(selectDoctor);
        st.setInt(1, doctor_id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new DOCTORSDto(rs);
        } else {
            return null;
        }
    }


//
//    public void updateDoctors(DOCTORS doct) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn= MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(updateDoctors);
//        st.setString(1, doct.getDoctor_name());
//        st.setString(2, doct.getDoctor_phone());
//        st.setInt(3, doct.getDoctor_id());
//        st.executeUpdate();
//        conn.close();
//    }


//    public ArrayList<DOCTORS> selectAllDoctors() throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(selectAllDoctors);
//        ResultSet rs = st.executeQuery();
//        ArrayList<DOCTORS> DOCT = new ArrayList<>();
//        while (rs.next()) {
//            DOCT.add(new DOCTORS(rs));
//        }
//        return DOCT;
//    }
    public ArrayList<DOCTORSDto> selectAllDoctors(DOCTORSFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st;
        if (filter.getDoctor_name()!= null) {
            st = conn.prepareStatement(searchDoctors__name);
            st.setString(1, filter.getDoctor_name());
        } else if (filter.getSpecialty() != null) {
            st = conn.prepareStatement(searchDoctors_specialty);
            st.setString(1, filter.getSpecialty());
        }else if (filter.getDoctor_id()!= null) {
            st = conn.prepareStatement(searchDoctor_ID);
            st.setInt(1, filter.getDoctor_id());
        }
        else {
            st = conn.prepareStatement(selectAllDoctors);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<DOCTORSDto> DOCT = new ArrayList<>();
        while (rs.next()) {
            DOCT.add(new DOCTORSDto(rs));
        }
        return DOCT;
    }


    public DOCTORS selectLogin_doctor(String Doctor_email ,String Doctor_password ) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(selectLogin_doctor);
        st.setString(1, Doctor_email);
        st.setString(2, Doctor_password);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new DOCTORS(rs);
        }
        else {
            return null;
        }
    }

}
