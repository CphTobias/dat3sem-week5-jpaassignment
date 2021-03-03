package com.insession.jpaassignment.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    private static final long serialVersionUID = -4519086971564709006L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamId")
    private Long teamId;

    @Column(name = "teamName")
    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonTeam> persons;

    public Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
        this.persons = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Team{" +
            "teamId=" + teamId +
            ", teamName='" + teamName + '\'' +
            ", persons=" + persons +
            '}';
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<PersonTeam> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonTeam> persons) {
        this.persons = persons;
    }
}
