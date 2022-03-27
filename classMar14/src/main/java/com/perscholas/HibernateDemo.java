package com.perscholas;

import com.perscholas.database.dao.ActorDAO;
import com.perscholas.database.dao.MovieActorDAO;
import com.perscholas.database.dao.MovieDAO;
import com.perscholas.database.entity.Actor;
import com.perscholas.database.entity.Movie;
import com.perscholas.database.entity.MovieActor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;


public class HibernateDemo {

    private ActorDAO actorDao = new ActorDAO();
    private MovieDAO movieDao = new MovieDAO();
    private MovieActorDAO movieActorDao = new MovieActorDAO();

    public void work() {
     //     read();
     //     create();
     //    delete();
     //   deleteWithQuery();
      //  update();
      //  queryFaL();
     //   addActorToMovie();
   //     queryManytoMany();
        queryOneToMany();
    //    addActorToMovie();

    }

    private void addActorToMovie() {
        //get Star wars from the db
        Movie movie = movieDao.findById(1);
    System.out.println(movie.toString());

        Actor actor = actorDao.findByID(12);

        MovieActor movieActor  = new MovieActor();
        movieActor.setMovie(movie);
        movieActor.setActor(actor);
        movieActor.setCharacterName("C3PO");
        movieActorDao.save(movieActor);


//        movie.getActors().add(luke);
//        System.out.println(luke.toString());
//      luke.getMovies().add(movie);
//        movieDao.update(movie);
 //       actorDao.update(luke);
    }

    private void queryOneToMany() {
        MovieActor movieActor = movieActorDao.findByID(2);
        System.out.println(movieActor);
        System.out.println("actor first name = " + movieActor.getActor().getFirstName());

       Actor a = actorDao.findByID(movieActor.getActorId());
       System.out.println("actor first name = " + a.getFirstName());
        System.out.println(movieActor);
        System.out.println(movieActor.getActor());
        System.out.println(movieActor.getMovie());

        System.out.println("trying to find movie by id");
        List<MovieActor> ma1 = movieActorDao.findByMovieId(1);
        System.out.println(ma1.toString());

        System.out.println("trying to find movie by title");
        List<Map<String,Object>> ma2 = movieActorDao.findByMovieTitle("Star Wars");
                System.out.println(ma2.toString());


    }
    private void queryManytoMany(){}
//
//        Movie movie = movieDao.findById(1);
//
//        System.out.println(movie);
//
//        System.out.println("------------");
//        Actor luke =actorDao.findByID(2);
//        System.out.println(luke);
//        for (Movie m : luke.getMovies() ) {
//            System.out.println(m);
//        }

//        Actor luke =actorDao.findByID(2);
//        luke.getMovies().add(movie);
//        actorDao.update(luke);
//
//        Actor ford = actorDao.findByID(9);
//
//        movie.getActors().add(luke);
//        movie.getActors().add(ford);
//
//        movieDao.update(movie);
//    }
    public void update() {
        Actor actor = actorDao.findByID(3);
        System.out.println("Before update" + actor);

        actor.setFirstName("This is");
        actor.setLastName("an Update");
        actor.setAge(10);

        actorDao.update(actor);

        actor = actorDao.findByID(3);
        System.out.println("After update" + actor);
    }
    public void delete() {
        Actor actor = actorDao.findByID(11);
        actorDao.delete(actor);
    }

        private void deleteWithQuery() {
            actorDao.deleteById(11);
    }

    public void read() {
        Integer actorId = 5;
        Actor actor = actorDao.findByID(actorId);
        if (actor == null) {
            System.out.println("Unable to find actor by id :" + actorId);
        } else {
            System.out.println(actor.toString());
        }

        List<Actor> actors = actorDao.findByFirstName("Mark");
        if (actors.isEmpty()) {
            System.out.println("No actors found");
        } else {
            for (Actor a : actors) {
                System.out.println("Find by First Name : " + a);
            }
        }
    }

    public void queryFaL() {
        List<Actor> actors = actorDao.findByFirstandLastName("Mark","Hamil");
        if (actors.isEmpty()) {
            System.out.println("No actors found");
        } else {
            for (Actor a : actors) {
                System.out.println("Find by First and Last Name : " + a);
            }
        }
    }


    public void create() {
        Actor actor = new Actor();

        actor.setFirstName("Chris");
        actor.setLastName("Pratt");
        actor.setAge(35);

        System.out.println("Before save : " + actor);
        actorDao.save(actor);
        System.out.println("After  save : " + actor);
    }

    public static void main(String[] args) {
        new HibernateDemo().work();
    //    HibernateDemo().create();
    }
//        entityMgr.getTransaction().begin();
//        Actor actor = new Actor();
//        actor.setFirstName("Harrison");
//        actor.setLastName("Ford");
//        actor.setAge(75);
//
//
//        entityMgr.persist(actor);
//
//
//
//        entityMgr.getTransaction().commit();
//        entityMgr.clear();
//        System.out.println("successfully created actor");
//
//    }
}
