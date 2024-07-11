package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CONSULTATIONS_update_rateDto {
    private int  consultation_id ;
    private int  rate ;


    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


    public CONSULTATIONS_update_rateDto(int consultation_id, int rate) {
        this.consultation_id = consultation_id;
        this.rate = rate;
    }

    public CONSULTATIONS_update_rateDto() {
    }


    @Override
    public String toString() {
        return "CONSULTATIONS_update_rateDto{" +
                "consultation_id=" + consultation_id +
                ", rate=" + rate +
                '}';
    }

    public CONSULTATIONS_update_rateDto(ResultSet rs) throws SQLException {
        consultation_id= rs.getInt("consultation_id");
        rate= rs.getInt("rate");

    }
}
