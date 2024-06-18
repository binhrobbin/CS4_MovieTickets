package vn.codegym.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameMovie;
    private String producer;
    private int time;
    private String typeMovie;
    private String image;
    @Column(columnDefinition = "LONGBLOB")
    private String description;
//    @OneToMany(mappedBy = "movie")
//    private List<MovieSchedule> movieScheduleList;
    public Movie() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getTypeMovie() {
        return typeMovie;
    }

    public void setTypeMovie(String typeMovie) {
        this.typeMovie = typeMovie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<MovieSchedule> getMovieScheduleList() {
//        return movieScheduleList;
//    }
//
//    public void setMovieScheduleList(List<MovieSchedule> movieScheduleList) {
//        this.movieScheduleList = movieScheduleList;
//    }
}
