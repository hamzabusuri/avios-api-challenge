package com.service.parentalcontrol.hamza.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public boolean exists = false;


    public boolean getMovieDetails(String movieId) throws TitleNotFoundException {
        MovieClassification itemRetrieved = mapper.load(MovieClassification.class, movieId);

        if(itemRetrieved != null){
            exists = true;
        }

        else{
            throw new TitleNotFoundException("The movie service could not find the given movie");
        }
        return exists;
    }
}