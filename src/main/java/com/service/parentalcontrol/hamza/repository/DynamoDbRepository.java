package com.service.parentalcontrol.hamza.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.model.MovieClassification;


@Repository
public class DynamoDbRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

    @Autowired
    private DynamoDBMapper mapper;


    public MovieClassification getMovieDetails(String movieId) {
        return mapper.load(MovieClassification.class, movieId);
    }
}