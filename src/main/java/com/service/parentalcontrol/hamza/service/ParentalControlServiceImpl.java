package com.service.parentalcontrol.hamza.service;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.ParentalControlLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.parentalcontrol.hamza.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class ParentalControlServiceImpl implements ParentalControlService {

    @Autowired
    private MovieService movieService;

    @Override
    public boolean checkParentalControlLevel(String parentalControlLevel, String movieId) throws TitleNotFoundException, TechnicalFailureException {
        boolean response = false;
        ParentalControlLevel parentalControlLevelPreference = ParentalControlLevel.findByLevel(parentalControlLevel);

        String movieParentalControl = movieService.getParentalControlLevel(movieId);
        ParentalControlLevel movieParentalControlLevel = ParentalControlLevel.findByLevel(movieParentalControl);

        if (movieParentalControlLevel != null && parentalControlLevelPreference != null) {
            if (movieParentalControlLevel.getValue() <= parentalControlLevelPreference.getValue()) {
                response = true;
            }
        }
        new TechnicalFailureException("System error");
        return response;
    }

}