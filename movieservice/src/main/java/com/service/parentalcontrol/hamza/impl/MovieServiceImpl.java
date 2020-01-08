package com.service.parentalcontrol.hamza.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This stubbed implementation is for purposes of demonstrating application functionality.
 */
@Component
public class MovieServiceImpl implements MovieService {

	@Autowired
	private DynamoDBMapper mapper;

	@Override
	public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		MovieClassification movie = mapper.load(MovieClassification.class, movieId);

		return movie.getIdentifier();
	}
}