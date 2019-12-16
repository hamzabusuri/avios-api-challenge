package com.service.parentalcontrol.hamza.service;


import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;

/**
 * Checks if movie is suitable for parental control level.
 */
public interface ParentalControlService {

    /**
     * Checks if movie is suitable for parental control level.
     * @param pclPreference Parental Control Level (Rating) preference
     * @param movieId id of movie
     * @return
     * @throws TitleNotFoundException If movie is not found.
     * @throws TechnicalFailureException If runtime error occurs.
     */
    boolean isMoviePermissible(String pclPreference, String movieId) throws TitleNotFoundException, TechnicalFailureException;
}