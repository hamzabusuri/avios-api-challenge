package com.service.parentalcontrol.hamza.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.service.parentalcontrol.hamza.model.MovieClassification;

@Repository
public class DynamoDbRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

    @Autowired
    private DynamoDBMapper mapper;

    public void insertIntoDynamoDB(MovieClassification movie) {
        mapper.save(movie);
    }

    public MovieClassification getMovieDetails(String movieId, String identifier) {
        return mapper.load(MovieClassification.class, movieId,identifier);
    }

}