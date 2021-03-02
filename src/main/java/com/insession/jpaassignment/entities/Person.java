/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insession.jpaassignment.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author tobiaszimmermann
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = -3332224278644415795L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    private String name;
    private int year;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(
        mappedBy = "person",
        cascade = CascadeType.PERSIST
    )
    List<Fee> fees;

    @ManyToMany(
        mappedBy = "persons",
        cascade = CascadeType.PERSIST
    )
    private List<SwimStyle> styles;

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
        this.fees = new ArrayList<>();
        this.styles = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Person{" + "personId=" + personId + ", name=" + name + ", year=" + year + '}';
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setPerson(this);
        }
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void addFee(Fee fee) {
        if (fee != null) {
            this.fees.add(fee);
            fee.setPerson(this);
        }
    }

    public void addSwimStyle(SwimStyle swimStyle) {
        if (swimStyle != null) {
            this.styles.add(swimStyle);
            swimStyle.getPersons().add(this);
        }
    }

    public void removeSwimStyle(SwimStyle swimStyle) {
        if (swimStyle != null) {
            styles.remove(swimStyle);
            swimStyle.getPersons().remove(this);
        }
    }

    public List<SwimStyle> getStyles() {
        return styles;
    }


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long p_id) {
        this.personId = p_id;
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

    public Address getAddress() {
        return address;
    }
}
