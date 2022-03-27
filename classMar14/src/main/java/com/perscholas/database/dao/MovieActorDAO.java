package com.perscholas.database.dao;


import com.perscholas.database.entity.Actor;
import com.perscholas.database.entity.MovieActor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MovieActorDAO {

    private final String PERSISTENCE_UNTI_NAME = "movies-db";

    private EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNTI_NAME);
    EntityManager entityMgr = emFactoryObj.createEntityManager();


    public MovieActor findByID(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actor where id = 1
        String sql = "SELECT ma FROM MovieActor ma WHERE ma.movieId = :maID";
        TypedQuery<MovieActor> query = em.createQuery(sql, MovieActor.class);  //Pass in the class type you want to cast to

        query.setParameter("maID", id);

        try {
            MovieActor result = query.getSingleResult();
            return result;
        } catch (Exception e) {
            return null;
        }

    }
    public List<MovieActor> findByMovieId(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT ma FROM MovieActor ma WHERE ma.movieId = :movieId";
        TypedQuery<MovieActor> query = em.createQuery(sql, MovieActor.class);  //Pass in the class type you want to cast to
        query.setParameter("movieId", id);

        List<MovieActor> result = query.getResultList();

        return result;
    }

    public List<Map<String,Object>> findByMovieTitle(String movieName) {
        EntityManager em = emFactoryObj.createEntityManager();

//        String sql = "SELECT ma FROM MovieActor ma JOIN Movie m on ma.movieId = m.id WHERE m.title = :movieName";
//        TypedQuery<MovieActor> query = em.createQuery(sql, MovieActor.class);  //Pass in the class type you want to cast to
//        query.setParameter("movieName", movieName);
//
//        List<MovieActor> result = query.getResultList();

        String sql = "SELECT m.title as title ,ma.characterName as cName FROM MovieActor ma JOIN Movie m on ma.movieId = m.id WHERE m.title = :movieName";
        Query query = em.createQuery(sql);
        query.setParameter("movieName", movieName);
        List<Map<String,Object>> rs = query.getResultList();
        return rs;
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
