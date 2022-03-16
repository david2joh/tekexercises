package com.perscholas.database.dao;

import com.perscholas.database.entity.Actor;

import javax.persistence.*;
import java.util.List;

public class ActorDAO {

    private final String PERSISTENCE_UNTI_NAME = "movies-db";

    private EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNTI_NAME);
    EntityManager entityMgr = emFactoryObj.createEntityManager();


    public Actor findByID(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actor where id = 1
        String sql = "SELECT a FROM Actor a WHERE a.id = :actorID";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);  //Pass in the class type you want to cast to

        query.setParameter("actorID", id);

        try {
            Actor result = query.getSingleResult();
            return result;
        } catch (Exception e) {
           return null;
        }

    }

    public List<Actor> findByFirstName(String firstName) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actor where id = 1
        String sql = "SELECT a FROM Actor a WHERE a.firstName = :fName";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);  //Pass in the class type you want to cast to
        query.setParameter("fName", firstName);

        List<Actor> result = query.getResultList();

        return result;
    }

    public List<Actor> findByFirstandLastName(String firstName, String lastName) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actor where fname,lname
        String sql = "SELECT a FROM Actor a WHERE a.firstName = :fName AND lastName = :lName";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);  //Pass in the class type you want to cast to
        query.setParameter("fName", firstName);
        query.setParameter("lName", lastName);

        List<Actor> result = query.getResultList();

        return result;
    }




    public int deleteById(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();
        // delete from actor where idString
        String sql = "DELETE FROM Actor a WHERE a.id = :actorId";
        Query query = em.createQuery(sql);
        query.setParameter("actorId", id);

        int count = query.executeUpdate();

        em.getTransaction().commit();

        return count;
    }

    public void delete (Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        //save the actor to the database
        em.remove(em.contains(actor) ? actor : em.merge(actor));

        //commit the transaction
        em.getTransaction().commit();
        em.clear();

        return;
    }
    public Actor save (Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        //save the actor to the database
        em.persist(actor);

        //commit the transaction
        em.getTransaction().commit();
        em.clear();

        return actor;
    }

    public Actor update(Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        //save the actor to the database
        em.merge(actor);

        //commit the transaction
        em.getTransaction().commit();
        em.clear();

        return actor;
    }

}
