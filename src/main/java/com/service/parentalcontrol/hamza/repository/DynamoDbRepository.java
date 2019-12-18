package com.service.parentalcontrol.hamza.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
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
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.util.Map;
import java.util.List;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import java.util.stream.Collectors;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;





@Repository
public class DynamoDbRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);
    boolean doesExist = false;

    @Autowired
    private DynamoDBMapper mapper;


    public MovieClassification getMovieDetails(String movieId) {
        return mapper.load(MovieClassification.class, movieId);
    }

    /*public boolean getMovieFromDBUsingMovieId(String movieId) throws TitleNotFoundException {
        MovieClassification item = mapper.load(MovieClassification.class, movieId);

        if(item != null){
            doesExist = true;
        }
        else{
            throw new TitleNotFoundException("Movie does not exist");
        }

        return doesExist;

    }*/
}