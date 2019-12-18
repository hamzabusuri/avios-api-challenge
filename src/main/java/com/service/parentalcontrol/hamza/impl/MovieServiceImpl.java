package com.service.parentalcontrol.hamza.impl;
import com.service.parentalcontrol.hamza.service.MovieService;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import org.springframework.stereotype.Service;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;

/**
 * This stubbed implementation is for purposes of demonstrating application functionality.
 */
@Service
public class MovieServiceImpl implements MovieService {

	private String movieId = "";
	private DynamoDbRepository repo;

	@Override
	public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		MovieClassification res = repo.getMovieDetails(movieId);

		return res.getIdentifier();
	}



}