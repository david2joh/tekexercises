package com.perscholas.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Temporal(TemporalType.DATE)
    @Column(name="release_date")
    private Date releaseDate;

//    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
//    private Set<Actor> actors = new HashSet<>();

       @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
       @ToString.Exclude
       private Set<MovieActor> movieActors;

        @Override
        public String toString() {
        return "movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public Set<Actor> getActors() {
//        return actors;
//    }
//
//    public void setActors(Set<Actor> actors) {
//        this.actors = actors;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//


}
