package com.insession.jpaassignment.dtos;

import com.insession.jpaassignment.entities.Fee;
import com.insession.jpaassignment.entities.Person;
import java.util.Date;
import java.util.List;

public class PersonFeesDTO {

    private String name;
    private int year;
    private int feeAmount;
    private Date payDate;

    public PersonFeesDTO(String name, int year, int feeAmount, Date payDate) {
        this.name = name;
        this.year = year;
        this.feeAmount = feeAmount;
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "PersonFeesDTO{" +
            "name='" + name + '\'' +
            ", year=" + year +
            ", feeAmount=" + feeAmount +
            ", payDate=" + payDate +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(int feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}

