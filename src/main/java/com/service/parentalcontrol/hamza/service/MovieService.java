package com.service.parentalcontrol.hamza.service;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;

import org.springframework.stereotype.Service;
/**
 * Gets rating for a given movie.
 */
@Service
public interface MovieService {

    /**
     *
     * @param movieId
     * @return Rating for a given movie.
     * @throws TitleNotFoundException If movie is not found.
     * @throws TechnicalFailureException If a runtime error occurs.
     */
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;

}