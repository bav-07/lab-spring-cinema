package com.example.spring_cinema.services;

import com.example.spring_cinema.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_cinema.repositories.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieService(){}

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    public void addMovie(String title, String rating, int duration) {
        Movie movie = new Movie(title, rating, duration);
        movieRepository.save(movie);
    }

    public void updateMovieById(int id, String title, String rating, int duration){

        Movie movie = movieRepository.findById(id).get();

        movie.setTitle(title);
        movie.setRating(rating);
        movie.setDuration(duration);

        movieRepository.save(movie);
    }

}

