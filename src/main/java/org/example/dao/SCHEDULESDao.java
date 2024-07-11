package org.example.dao;
import org.example.db.MCPConnection;
import org.example.dto.*;
import org.example.helpers.MCPConstants;
import org.example.models.CONSULTATIONS;
import org.example.models.PATIENTS;
import org.example.models.SCHEDULES;

import java.sql.*;
import java.util.ArrayList;

public class SCHEDULESDao {
    // Doctor should be able to define and manage availability schedule
    private static final String selectAllSCHEDULES = " select * from SCHEDULES ";
    private static final String insertSCHEDULES = "insert into SCHEDULES (doctor_id,start_time,end_time,is_available) " + "values (?,?,?," + MCPConstants.SCHEDULES_is_available_doctor + ")";
    private static final String updateSCHEDULES = "update SCHEDULES set start_time = ?, end_time = ? , is_available = ? where id  = ?";
    private static final String select_SCHEDULE_with_DOC_available  ="select * from SCHEDULES where doctor_id = ? AND is_available = ? ";

    //Search by available
    private static final String select_SCHEDULE_with_available  ="select doctor_id from SCHEDULES where is_available = ? ";



    public ArrayList<is_availableDto> select_SCHEDULE_with_available(boolean is_available) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(select_SCHEDULE_with_available);
        st.setBoolean(1, is_available);
        ResultSet rs = st.executeQuery();
        ArrayList<is_availableDto> is_availableDtos = new ArrayList<>();
        while (rs.next()) {
            is_availableDtos.add(new is_availableDto(rs));
        }
        return is_availableDtos;
    }

    public void insertSCHEDULES(SCHEDULESDto s) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(insertSCHEDULES);
        st.setInt(1,s.getDoctor_id());
        st.setString(2,s.getStart_time().toString());
        st.setString(3,s.getEnd_time().toString());
        st.executeUpdate();
        conn.close();
    }

    public void updateSCHEDULES(SCHEDULESDto s) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(updateSCHEDULES);
        st.setString(1,s.getStart_time().toString());
        st.setString(2,s.getEnd_time().toString());
        st.setBoolean(3, s.getIs_available());
        st.setInt(4, s.getId());
        st.executeUpdate();
        conn.close();
    }



    public ArrayList<SCHEDULESDto> selectAllSCHEDULES(SCHEDULESFilterDto filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn= MCPConnection.getConn();
        PreparedStatement st;
        if (filter.getDoctor_id() != null && filter.getIs_available() != null) {
            st = conn.prepareStatement(select_SCHEDULE_with_DOC_available);
            st.setInt(1, filter.getDoctor_id());
            st.setBoolean(2, filter.getIs_available());
        }
        else if (filter.getIs_available() != null) {
            st = conn.prepareStatement(select_SCHEDULE_with_available);
            st.setBoolean(1, filter.getIs_available());
        }
        else {
            st = conn.prepareStatement(selectAllSCHEDULES);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<SCHEDULESDto> SCHE = new ArrayList<>();
        while (rs.next()) {
            SCHE.add(new SCHEDULESDto(rs));
        }
        return SCHE;
    }


}
