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
        String movieParentalControlLevel = getParentalControlLevel(movieId);
        boolean result = false;

        if (getParentalControlLevel(movieParentalControlLevel) == "U" && getParentalControlLevel(userPreference) =="U"){
            result = true;
        }

        return result;
    }

    private String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        try {
            String controlLevel = getParentalControlLevel(movieId);
            return controlLevel;
        } catch (TechnicalFailureException | TitleNotFoundException ex) {
            //Log exception here
            throw ex;
        } catch (Exception ex) {
            //RuntimeExceptions being thrown from MovieService
            //Log exception here
            throw new TechnicalFailureException("There is some problem with Movie Service");
        }
    }
}