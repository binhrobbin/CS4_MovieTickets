package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> findAll();
    Page<Movie> findAll(Pageable pageable);
    void save(Movie movie);

    void update(long id,Movie movie);
    void update(Movie movie);
    void remove(long id);
    Optional<Movie> findById(long id);
    List<Movie> findMoviesByNameMovie(String name);
    Optional<List<Movie>> findMoviesByNameMovieContaining(String keyword);
    Optional<Movie> findMovieByNameMovieContaining(String keyword);
    List<Movie> findMoviesByTypeMovieContaining(String type);
    List<Movie> findMoviesByProducer(String producer);
}
