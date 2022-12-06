package services;

import models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieService(){}

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }



}

