package com.insession.jpaassignment.dtos;

public class PersonSwimStyleDTO {

    private String name;
    private int year;
    private String swimStyle;

    public PersonSwimStyleDTO(String name, int year, String swimStyle) {
        this.name = name;
        this.year = year;
        this.swimStyle = swimStyle;
    }

    @Override
    public String toString() {
        return "PersonSwimStyleDTO{" +
            "name='" + name + '\'' +
            ", year=" + year +
            ", swimStyle='" + swimStyle + '\'' +
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

    public String getSwimStyle() {
        return swimStyle;
    }

    public void setSwimStyle(String swimStyle) {
        this.swimStyle = swimStyle;
    }
}
