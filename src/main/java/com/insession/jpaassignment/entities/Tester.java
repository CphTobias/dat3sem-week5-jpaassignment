package com.insession.jpaassignment.entities;

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

        person1.addFee(fee1);
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
    }

}
