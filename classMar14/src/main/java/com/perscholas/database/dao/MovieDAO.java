package com.perscholas.database.dao;

import com.perscholas.database.entity.Actor;
import com.perscholas.database.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MovieDAO {
    private final String PERSISTENCE_UNTI_NAME = "movies-db";

    private EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNTI_NAME);
    EntityManager entityMgr = emFactoryObj.createEntityManager();


    public Movie findById(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from movie where id
        String sql = "SELECT m FROM Movie m WHERE m.id = :movieId";
        TypedQuery<Movie> query = em.createQuery(sql, Movie.class);  //Pass in the class type you want to cast to
        query.setParameter("movieId", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public Movie save (Movie movie) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        //save the movieto the database
        em.persist(movie);

        //commit the transaction
        em.getTransaction().commit();
        em.clear();

        return movie;
    }

    public Movie update(Movie movie) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        //save the actor to the database
        em.merge(movie);

        //commit the transaction
        em.getTransaction().commit();
        em.clear();

        return movie;
    }

}
