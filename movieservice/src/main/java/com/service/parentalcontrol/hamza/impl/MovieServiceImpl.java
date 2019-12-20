package com.service.parentalcontrol.hamza.impl;
import com.service.parentalcontrol.hamza.service.MovieService;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;

/**
 * This stubbed implementation is for purposes of demonstrating application functionality.
 */
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private DynamoDbRepository repo;

	@Override
	public MovieClassification getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		MovieClassification movie = repo.getMovieDetails(movieId);

		return movie;
	}
}