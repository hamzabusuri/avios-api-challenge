package com.service.parentalcontrol.hamza.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;
import com.service.parentalcontrol.hamza.service.MovieService;
import com.service.parentalcontrol.hamza.service.ParentalControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParentalControlServiceImpl implements ParentalControlService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private DynamoDbRepository repo;

    boolean result = false;
    boolean exists = false;

    public ParentalControlServiceImpl (MovieService movieService) {
        this.movieService = movieService;
    }

    public ParentalControlServiceImpl (DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public ParentalControlServiceImpl () {
    }

    @Override
    public boolean checkParentalControlLevel(String movieId, String userPreference) throws TitleNotFoundException, TechnicalFailureException {
        boolean mExists = repo.getMovieDetails(movieId);
        if(mExists){
            String movie = movieService.getParentalControlLevel(movieId);


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
                throw new TitleNotFoundException("The movie service could not find the given movie");
                }

        return result;
    }


    private void isDefault() throws TechnicalFailureException {
        throw new TechnicalFailureException("System error");
    }

    private void canWatchU(String str){
        if(str.contains("U")){
            result = true;
        }

        else{
            result = false;
        }

    }

    private void canWatchPG(String str){
        if(str.contains("U") || str.contains("PG")){
            result = true;
        }
        else{
            result = false;
        }

    }

    private void canWatch12(String str){
        if(str.contains("U") || str.contains("PG") || str.contains("12")){
            result = true;
        }
        else{
            result = false;
        }

    }

    private void canWatch15(String str){
        if(str.contains("U") || str.contains("PG") || str.contains("12") || str.contains("15")){
            result = true;
        }
        else{
            result = false;
        }

    }

    private void canWatch18(String str){
        if(str.contains("U") || str.contains("PG") || str.contains("12") || str.contains("15") || str.contains("18")){
            result = true;
        }
        else{
            result = false;
        }

    }
}