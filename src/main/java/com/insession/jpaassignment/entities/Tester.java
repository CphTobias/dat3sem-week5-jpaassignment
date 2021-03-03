package com.insession.jpaassignment.entities;

import com.insession.jpaassignment.dtos.PersonFeesDTO;
import com.insession.jpaassignment.dtos.PersonSwimStyleDTO;
import com.insession.jpaassignment.entities.PersonTeam.SwimmerLevel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Address address1 = new Address("Bob street", 2001, "Bob town");
        Address address2 = new Address("Bob 2 street", 2001, "Bob 2 town");

        Person person1 = new Person("Bob", 1998);
        Person person2 = new Person("Bob2", 2000);

        person1.setAddress(address1);
        person2.setAddress(address2);

        Fee fee1 = new Fee(200);
        Fee fee2 = new Fee(300);
        Fee fee3 = new Fee(600);

        person1.addFee(fee1);
        person1.addFee(fee3);
        person2.addFee(fee2);

        List<Fee> fees;

        SwimStyle swimStyle1 = new SwimStyle("Crawl");
        SwimStyle swimStyle2 = new SwimStyle("Butterfly");
        SwimStyle swimStyle3 = new SwimStyle("Breast stroke");

        person1.addSwimStyle(swimStyle1);
        person1.addSwimStyle(swimStyle2);
        person2.addSwimStyle(swimStyle3);

        em.getTransaction().begin();
        em.persist(person1);
        em.persist(person2);
        em.getTransaction().commit();

        fees = em.createQuery("SELECT f FROM Fee f", Fee.class).getResultList();

        em.getTransaction().begin();
        person1.removeSwimStyle(swimStyle2);
        em.getTransaction().commit();

        System.out.println(person1);
        System.out.println(person1.getAddress());
        System.out.println(person2);
        System.out.println(person2.getAddress());

        System.out.println(address1.getPerson().getName());
        person1.getFees().forEach(System.out::println);

        fees.forEach(System.out::println);

        populate(em);

        //Lesson 2: Create JPQL queries
        //Find and print out all persons and their fees
        List<PersonFeesDTO> personFeesDTO = em.createQuery(
            "SELECT new com.insession.jpaassignment.dtos.PersonFeesDTO(p.name, p.year, f.amount, f.payDate) FROM Person p JOIN p.fees f",
            PersonFeesDTO.class).getResultList();

        //this way we get kind of a receipt on each fee
        personFeesDTO.forEach(System.out::println);

        //Find and print out all persons and count their number swim styles
        List<PersonSwimStyleDTO> personSwimStyleDTOS = em.createQuery(
            "SELECT new com.insession.jpaassignment.dtos.PersonSwimStyleDTO(p.name, p.year, s.styleName) FROM Person p JOIN p.styles s",
            PersonSwimStyleDTO.class)
            .getResultList();

        personSwimStyleDTOS.forEach(System.out::println);

        //Find all person that has a swimstyle named 'Crawl'
        List<Person> peopleWithCrawl = em
            .createQuery("SELECT p FROM Person p JOIN p.styles s WHERE s.styleName = :style", Person.class)
            .setParameter("style", "Crawl")
            .getResultList();

        peopleWithCrawl.forEach(person -> System.out.println("Person with crawl: " + person.getName() + person.getStyles()));

        //Find the sum of all Fees
        long sumOfFees = (long) em.createQuery("SELECT SUM(f.amount) FROM Fee f").getSingleResult();
        System.out.println("Sum of all fees: " + sumOfFees);

        //Find the smallest Fee and the highest
        Integer smallestFee = (Integer) em.createQuery("SELECT MIN(f.amount) FROM Fee f").getSingleResult();
        System.out.println("Smallest Fee: " + smallestFee);

        Integer heighestFee = (Integer) em.createQuery("SELECT MAX(f.amount) FROM Fee f").getSingleResult();
        System.out.println("Highest Fee: " + heighestFee);

        //Inserting after creation of the link_person_team table
        Team team1 = new Team("Bobs team");
        Team team2 = new Team("Bob2s team");

        person1.addTeam(team1, SwimmerLevel.HIGH);
        person2.addTeam(team2, SwimmerLevel.LOW);

        em.getTransaction().begin();
        em.persist(team1);
        em.persist(team2);
        em.getTransaction().commit();

        List<PersonTeam> highLevelPersonTeams = em.createQuery("SELECT pt FROM PersonTeam pt WHERE pt.level = :level", PersonTeam.class)
            .setParameter("level", SwimmerLevel.HIGH)
            .getResultList();

        highLevelPersonTeams.forEach(System.out::println);

        em.close();
    }

    public static void populate(EntityManager em) {

        Person p3 = new Person("Jens", 1961);
        Person p4 = new Person("Ole", 1979);
        Person p5 = new Person("Bente", 1983);
        Person p6 = new Person("Dennis", 1939);
        Person p7 = new Person("Ida", 1990);
        Person p8 = new Person("Mette", 1999);
        Person p9 = new Person("Kaj", 1993);
        Person p10 = new Person("Finn", 2002);
        Person p11 = new Person("Charlotte", 2003);
        Person p12 = new Person("Karin", 1970);
        Person p13 = new Person("Gitte", 1975);
        Person p14 = new Person("Hans", 1989);

        Address a1 = new Address("Storegade 10", 2323, "Nr. Søby");
        Address a2 = new Address("Bredgade 14", 1212, "København K");
        Address a3 = new Address("Lillegade 1", 2323, "Nr. Søby");
        Address a4 = new Address("Damvej", 1212, "København K");
        Address a5 = new Address("Ndr Frihavnsgade 12", 2100, "Kbh Ø");
        Address a6 = new Address("Østerbrogade 85", 1212, "København K");
        Address a7 = new Address("Nørregade 4", 2200, "Nr. Søby");
        Address a8 = new Address("Nørregade 5", 2200, "København K");
        Address a9 = new Address("Odensegade 64", 2323, "Nr. Søby");
        Address a10 = new Address("Århusgade 29", 2300, "København S");

        p3.setAddress(a1);
        p4.setAddress(a2);
        p5.setAddress(a3);
        p6.setAddress(a4);
        p7.setAddress(a5);
        p8.setAddress(a6);
        p9.setAddress(a7);
        p10.setAddress(a8);
        p11.setAddress(a9);
        p12.setAddress(a10);
        p13.setAddress(a1);
        p14.setAddress(a2);

        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);

        p3.addFee(f1);
        p4.addFee(f3);
        p5.addFee(f2);
        p6.addFee(f1);
        p7.addFee(f3);
        p8.addFee(f2);
        p9.addFee(f1);
        p10.addFee(f3);
        p11.addFee(f2);
        p12.addFee(f1);
        p13.addFee(f3);
        p14.addFee(f2);

        em.getTransaction().begin();
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.persist(p6);
        em.persist(p7);
        em.persist(p8);
        em.persist(p9);
        em.persist(p10);
        em.persist(p11);
        em.persist(p12);
        em.persist(p13);
        em.persist(p14);
        em.getTransaction().commit();

    }

}
