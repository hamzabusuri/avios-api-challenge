package com.service.parentalcontrol.hamza.impl;
import com.service.parentalcontrol.hamza.service.ParentalControlService;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.service.MovieService;

@Component
public class ParentalControlServiceImpl implements ParentalControlService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private DynamoDbRepository repo;


    public ParentalControlServiceImpl() {
    }

    public ParentalControlServiceImpl(MovieService movieService) throws TechnicalFailureException {

        if (movieService == null) {
            // log information here
            throw new TechnicalFailureException("Movie service is unavailable ");
        }
        this.movieService = movieService;
    }

    @Override
    public boolean checkParentalControlLevel(String movieId, String userPreference) throws TitleNotFoundException, TechnicalFailureException {
        MovieClassification movieParentalControlLevel = repo.getMovieDetails(movieId);
        String movie = movieParentalControlLevel.getIdentifier();
        boolean result = false;

        if (movie.equals(userPreference)){
            result = true;
        }

        return result;
    }
}