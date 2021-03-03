package com.insession.jpaassignment.entities;

import com.insession.jpaassignment.entities.PersonTeam.SwimmerLevel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = -3332224278644415795L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Long personId;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
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

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonTeam> teams;

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
        this.fees = new ArrayList<>();
        this.styles = new ArrayList<>();
        this.teams = new ArrayList<>();
    }


    public void setAddress(Address address) {
        if (address != null) {
            this.address = address;
            address.setPerson(this);
        }
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

    public void addTeam(Team team, SwimmerLevel swimmerLevel) {
        if (team != null) {
            PersonTeam personTeam = new PersonTeam(this, team, swimmerLevel);
            teams.add(personTeam);
            team.getPersons().add(personTeam);
        }
    }

    public void removeTeam(Team team) {
        Iterator<PersonTeam> iterator = teams.iterator();

        while (iterator.hasNext()) {
            PersonTeam pt = iterator.next();
            if (pt.getPerson().equals(this) && pt.getTeam().equals(team)){
                iterator.remove();   // fjernes fra teams i personens arrayliste
                pt.getTeam().getPersons().remove(pt);  // fjern person fra teamets person arrayliste
            }

        }
    }

    @Override
    public String toString() {
        return "Person{" + "personId=" + personId + ", name=" + name + ", year=" + year + '}';
    }

    public List<Fee> getFees() {
        return fees;
    }

    public List<SwimStyle> getStyles() {
        return styles;
    }

    public List<PersonTeam> getTeams() {
        return teams;
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
