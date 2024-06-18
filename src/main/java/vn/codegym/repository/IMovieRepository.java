package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.codegym.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMoviesByNameMovie(String name);
    Optional<List<Movie>> findMoviesByNameMovieContaining(String keyword);
    Optional<Movie> findMovieByNameMovieContaining(String keyword);
    List<Movie> findMoviesByTypeMovieContaining(String type);
    List<Movie> findMoviesByProducer(String producer);
    @Query(value = "select * from Movie where name = :keyword", nativeQuery = true)
    List<Movie> searchByName(@Param("keyword") String name);
}
