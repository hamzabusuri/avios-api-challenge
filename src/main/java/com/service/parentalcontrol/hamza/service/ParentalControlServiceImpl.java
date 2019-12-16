package com.service.parentalcontrol.hamza.service;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.ParentalControlLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.parentalcontrol.hamza.service.MovieService;

@Service
public class ParentalControlServiceImpl implements ParentalControlService {

    MovieService movieService;

    public ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public boolean isMoviePermissible(String pclPreference, String movieId) throws TitleNotFoundException, TechnicalFailureException {

        boolean isMoviePermissible;

        String pclMovie = movieService.getParentalControlLevel(movieId);

        int pclMovieValue = ParentalControlLevel.get(pclMovie);
        int pclPreferenceValue = ParentalControlLevel.get(pclPreference);

        return pclMovieValue > pclPreferenceValue ? false : true;
    }
}