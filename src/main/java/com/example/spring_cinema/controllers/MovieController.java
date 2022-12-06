package com.example.spring_cinema.controllers;

import com.example.spring_cinema.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.spring_cinema.services.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id){
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestParam(value="title") String title,
                                          @RequestParam(value="rating") String rating,
                                          @RequestParam(value="duration") int duration){

        movieService.addMovie(title, rating, duration);
        return new ResponseEntity<>(title + " movie added", HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> updateMovieById(@PathVariable int id,
                                                  @RequestParam(value="title") String title,
                                                  @RequestParam(value="rating") String rating,
                                                  @RequestParam(value="duration") int duration){
        if (movieService.getMovieById(id).isPresent()){
            movieService.updateMovieById(id, title, rating, duration);
            return new ResponseEntity<>("movie updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable int id){
        if (movieService.getMovieById(id).isPresent()){
            movieService.deleteMovieById(id);
            return new ResponseEntity<>("movie deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
