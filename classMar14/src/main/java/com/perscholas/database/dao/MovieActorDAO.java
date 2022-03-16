package com.perscholas.database.dao;


import com.perscholas.database.entity.Actor;
import com.perscholas.database.entity.MovieActor;

import javax.persistence.*;
import java.util.List;


public class MovieActorDAO {

    private final String PERSISTENCE_UNTI_NAME = "movies-db";

    private EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNTI_NAME);
    EntityManager entityMgr = emFactoryObj.createEntityManager();


    public MovieActor findByID(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actor where id = 1
        String sql = "SELECT ma FROM MovieActor ma WHERE ma.id = :maID";
        TypedQuery<MovieActor> query = em.createQuery(sql, MovieActor.class);  //Pass in the class type you want to cast to

        query.setParameter("maID", id);

        try {
            MovieActor result = query.getSingleResult();
            return result;
        } catch (Exception e) {
            return null;
        }

    }

    public MovieActor update(MovieActor ma) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        //save the actor to the database
        em.merge(ma);

        //commit the transaction
        em.getTransaction().commit();
        em.clear();

        return ma;
    }

    public MovieActor save (MovieActor ma) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        //save the actor to the database
        em.persist(ma);

        //commit the transaction
        em.getTransaction().commit();
        em.clear();

        return ma;
    }


}
