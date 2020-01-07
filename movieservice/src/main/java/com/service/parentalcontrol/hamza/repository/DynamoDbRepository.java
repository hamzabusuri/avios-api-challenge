package com.service.parentalcontrol.hamza.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbRepository {

    @Autowired
    private DynamoDBMapper mapper;


    public MovieClassification getMovieDetails(String movieId) {
        return mapper.load(MovieClassification.class, movieId);
    }
}