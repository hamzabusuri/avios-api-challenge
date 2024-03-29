package com.service.parentalcontrol.hamza.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@DynamoDBTable(tableName = "movies")
public @Data class MovieClassification implements Serializable{

    public MovieClassification(String movieId, String identifier) {
        this.movieId = movieId;
        this.identifier = identifier;
    }

    public MovieClassification(){

    }

    @DynamoDBHashKey(attributeName = "movieId")
    private String movieId;

    @DynamoDBAttribute(attributeName = "identifier")
    private String identifier;

}