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

    boolean result = false;

    public ParentalControlServiceImpl() {
    }

    public ParentalControlServiceImpl(MovieService movieService) throws TechnicalFailureException {

        if (movieService == null) {
            throw new TechnicalFailureException("Movie service is unavailable");
        }
        this.movieService = movieService;
    }

    @Override
    public boolean checkParentalControlLevel(String movieId, String userPreference) throws TitleNotFoundException, TechnicalFailureException {
        boolean movieExists = repo.movieExists(movieId);

        if (movieExists) {
            MovieClassification movieParentalControlLevel = movieService.getParentalControlLevel(movieId);
            String movie = movieParentalControlLevel.getIdentifier();

            switch (userPreference) {
                case "U":
                    canWatchU(movie);
                    break;
                case "PG":
                    canWatchPG(movie);
                    break;
                case "12":
                    canWatch12(movie);
                    break;
                case "15":
                    canWatch15(movie);
                    break;
                case "18":
                    canWatch18(movie);
                    break;
                default:
                    isDefault();
                    break;

            }
        }

        else{
            throw new TitleNotFoundException("Movie does not exist");
        }
        return result;
    }


    private void isDefault() throws TechnicalFailureException {
        throw new TechnicalFailureException("Invalid control level entered");
    }

    private void canWatchU(String str) throws TechnicalFailureException {
        if(str.contains("U")){
            result = true;
        }

        else{
            result = false;
        }

    }

    private void canWatchPG(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG")){
            result = true;
        }
        else{
            result = false;
        }

    }

    private void canWatch12(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG") || str.contains("12")){
            result = true;
        }
        else{
            result = false;
        }

    }

    private void canWatch15(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG") || str.contains("12") || str.contains("15")){
            result = true;
        }
        else{
            result = false;
        }

    }

    private void canWatch18(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG") || str.contains("12") || str.contains("15") || str.contains("18")){
            result = true;
        }
        else{
            result = false;
        }

    }
}