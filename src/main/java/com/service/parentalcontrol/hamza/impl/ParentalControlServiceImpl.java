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

    boolean result = false;

    private final String parental_level_error = "Under age with given level";


    public ParentalControlServiceImpl() {
    }

    public ParentalControlServiceImpl(MovieService movieService) throws TechnicalFailureException {

        if (movieService == null) {
            // log information here
            throw new TechnicalFailureException("Movie service is unavailable");
        }
        this.movieService = movieService;
    }

    @Override
    public boolean checkParentalControlLevel(String movieId, String userPreference) throws TitleNotFoundException, TechnicalFailureException {
        MovieClassification movieParentalControlLevel = movieService.getParentalControlLevel(movieId);
        String movie = movieParentalControlLevel.getIdentifier();

        //check if movie exists otherwise throw TitleNotFoundException



        switch (userPreference){
            case "U": watchLevelU(movie);
                break;
            case "PG": watchLevelPG(movie);
                break;
            case "12": watchLevel12(movie);
                break;
            case "15": watchLevel15(movie);
                break;
            case "18": watchLevel18(movie);
                break;
            default: defaultLevel();
                break;

        }

        return result;
    }

    private void defaultLevel() throws TechnicalFailureException {
        throw new TechnicalFailureException("Incorrect parental control level");
    }

    private void watchLevelU(String str) throws TechnicalFailureException {
        if(str.contains("U")){
            result = true;
        }
        else{
            throw new TechnicalFailureException(parental_level_error);
        }
    }

    private void watchLevelPG(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG")){
            result = true;
        }
        else{

            throw new TechnicalFailureException(parental_level_error);
        }

    }

    private void watchLevel12(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG") || str.contains("12")){
            result = true;
        }
        else{
            throw new TechnicalFailureException(parental_level_error);
        }

    }

    private void watchLevel15(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG") || str.contains("12") || str.contains("15")){
            result = true;
        }
        else{
            throw new TechnicalFailureException(parental_level_error);
        }

    }

    private void watchLevel18(String str) throws TechnicalFailureException {
        if(str.contains("U") || str.contains("PG") || str.contains("12") || str.contains("15") || str.contains("18")){
            result = true;
        }
        else{
            throw new TechnicalFailureException(parental_level_error);
        }

    }
}