package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.entity.Movie;
import vn.codegym.service.IMovieService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/movies")
public class R_MovieController {
    @Autowired
    IMovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> listMovies(){
        List<Movie> list = movieService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        movieService.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovie(@PathVariable Long id){
        Optional<Movie> MovieOptional = movieService.findById(id);
        if (!MovieOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MovieOptional.get(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id){
        Optional<Movie> MovieOptional = movieService.findById(id);
        if (!MovieOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieService.remove(id);
        return new ResponseEntity<>(MovieOptional.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        Optional<Movie> MovieOptional = movieService.findById(id);
        if (!MovieOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieService.update(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
