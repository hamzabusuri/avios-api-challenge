package com.service.parentalcontrol.hamza.model;

import lombok.*;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "movies")
public @Data class MovieClassification implements Serializable{

    @DynamoDBHashKey(attributeName = "movieId")
    private String movieId;

    @DynamoDBAttribute(attributeName = "identifier")
    private String identifier;

}