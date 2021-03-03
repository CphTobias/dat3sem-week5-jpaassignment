package com.insession.jpaassignment.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "link_person_team")
public class PersonTeam implements Serializable {

    public enum SwimmerLevel {
        LOW, MEDIUM, HIGH
    }

    private static final long serialVersionUID = -5369717875005744066L;

    @EmbeddedId
    private PersonTeamId id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "teamId")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private SwimmerLevel level;

    public PersonTeam() {
    }

    public PersonTeam(Person person, Team team,
        SwimmerLevel level) {
        this.id = new PersonTeamId(person.getPersonId(), team.getTeamId());
        this.person = person;
        this.team = team;
        this.level = level;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public SwimmerLevel getLevel() {
        return level;
    }

    public void setLevel(SwimmerLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonTeam)) {
            return false;
        }
        PersonTeam that = (PersonTeam) o;
        return Objects.equals(id, that.id) && Objects.equals(getPerson(), that.getPerson())
            && Objects.equals(getTeam(), that.getTeam()) && getLevel() == that.getLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getPerson(), getTeam(), getLevel());
    }

    @Override
    public String toString() {
        return "PersonTeam{" +
            "id=" + id +
            ", person=" + person +
            ", team=" + team +
            ", level=" + level +
            '}';
    }
}
