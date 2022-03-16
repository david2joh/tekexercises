package com.perscholas.database.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "movie_actors")
public class MovieActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="actor_id",insertable=false, updatable = false)
    private Integer actorId;

    @Column(name="movie_id",insertable=false, updatable = false)
    private Integer movieId;

    @Column(name="character_name")
    private String characterName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actor_id", nullable = false)
    @ToString.Exclude
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    @ToString.Exclude
    private Movie movie;

    @Override
    public String toString() {
        return "MovieActor{" +
                "id=" + id +
                ", characterName='" + characterName + '\'' +
                '}';
    }

}
