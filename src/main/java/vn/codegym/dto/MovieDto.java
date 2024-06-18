package vn.codegym.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import vn.codegym.entity.MovieSchedule;

import java.util.List;
import java.util.Objects;

public class MovieDto {
    private Long id;
    private String nameMovie;
    private String producer;
    private int time;
    private String typeMovie;
    private String image;
    private String description;
    private List<MovieSchedule> movieScheduleList;

    public MovieDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
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

    public List<MovieSchedule> getMovieScheduleList() {
        return movieScheduleList;
    }

    public void setMovieScheduleList(List<MovieSchedule> movieScheduleList) {
        this.movieScheduleList = movieScheduleList;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto that = (MovieDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
