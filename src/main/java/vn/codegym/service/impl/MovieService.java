package vn.codegym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.entity.Movie;
import vn.codegym.repository.IMovieRepository;
import vn.codegym.service.IMovieService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository movieRepository;
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void update(long id, Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void update(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void remove(long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findById(long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findMoviesByNameMovie(String name) {
        return movieRepository.findMoviesByNameMovie(name);
    }

    @Override
    public Optional<List<Movie>> findMoviesByNameMovieContaining(String keyword) {
        return movieRepository.findMoviesByNameMovieContaining(keyword);
    }

    @Override
    public Optional<Movie> findMovieByNameMovieContaining(String keyword) {
        return movieRepository.findMovieByNameMovieContaining(keyword);
    }

    @Override
    public List<Movie> findMoviesByTypeMovieContaining(String type) {
        return movieRepository.findMoviesByTypeMovieContaining(type);
    }

    @Override
    public List<Movie> findMoviesByProducer(String producer) {
        return movieRepository.findMoviesByProducer(producer);
    }
}
