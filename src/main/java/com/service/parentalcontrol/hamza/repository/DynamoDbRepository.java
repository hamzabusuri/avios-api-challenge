package com.service.parentalcontrol.hamza.repository;

import java.util.HashMap;
import java.util.List;
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
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@Repository
public class DynamoDbRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

    @Autowired
    private DynamoDBMapper mapper;


    public MovieClassification getMovieDetails(String movieId) {
        return mapper.load(MovieClassification.class, movieId);
    }
}