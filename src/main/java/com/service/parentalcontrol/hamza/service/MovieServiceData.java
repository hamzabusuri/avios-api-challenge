package com.service.parentalcontrol.hamza.service;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This stubbed implementation is for purposes of demonstrating application functionality.
 */
@Service
public class MovieServiceData implements MovieService {

    /**
     * By default this stubbed method returns an "18" rating if a movieId is not recognised
     */
    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {

        if (movieId.equalsIgnoreCase("alpha")) return "U";
        if (movieId.equalsIgnoreCase("beta")) return "PG";
        if (movieId.equalsIgnoreCase("charlie")) return "12";
        if (movieId.equalsIgnoreCase("delta")) return "15";
        if (movieId.equalsIgnoreCase("echo")) return "18";
        if (movieId.equalsIgnoreCase( "foxtrot")) throw new TechnicalFailureException();

        else throw new TitleNotFoundException();
    }
}