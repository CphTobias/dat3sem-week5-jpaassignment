package com.insession.jpaassignment.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class PersonTeamId implements Serializable {

    private static final long serialVersionUID = 564888114197189901L;

    private Long personId;
    private Long teamId;

    public PersonTeamId() {
    }

    public PersonTeamId(Long personId, Long teamId) {
        this.personId = personId;
        this.teamId = teamId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonTeamId)) {
            return false;
        }
        PersonTeamId that = (PersonTeamId) o;
        return Objects.equals(getPersonId(), that.getPersonId()) && Objects
            .equals(getTeamId(), that.getTeamId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getTeamId());
    }

    @Override
    public String toString() {
        return "PersonTeamId{" +
            "personId=" + personId +
            ", teamId=" + teamId +
            '}';
    }
}
