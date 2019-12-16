package com.service.parentalcontrol.hamza.controller;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.service.ParentalControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Hosts the endpoint over HTTP GET.
 */
@RestController
public class ParentalController {

    @Autowired
    ParentalControlService parentalControlService;

    @GetMapping("/permission/level/{pclPreference}/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isMoviePermissible(@PathVariable String pclPreference,
                                      @PathVariable String movieId) throws TitleNotFoundException, TechnicalFailureException {

        return parentalControlService.isMoviePermissible(pclPreference, movieId);
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND,reason="The movie service could not find the given movie")
    @ExceptionHandler(TitleNotFoundException.class)
    public void handleTitleNotFoundException(){}

    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="There has been a system error")
    @ExceptionHandler(TechnicalFailureException.class)
    public void handleTechnicalFailureException(){}

}