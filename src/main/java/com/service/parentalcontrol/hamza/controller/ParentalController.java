package com.service.parentalcontrol.hamza.controller;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.service.ParentalControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


//hosting the endpoint to connect to restful API service.
@RestController
public class ParentalController {

    //inject parental control service into controller to use
    @Autowired
    ParentalControlService parentalControlService;

    //create a mapping for the endpoint
    @GetMapping("/permission/level/{pclPreference}/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isMoviePermissible(@PathVariable String pclPreference,
                                      @PathVariable String movieId) throws TitleNotFoundException, TechnicalFailureException {

        return parentalControlService.checkParentalControlLevel(pclPreference, movieId);
    }

    //Throw TitleNotFoundException or TechnicalFailure exception for movies
    @ResponseStatus(value=HttpStatus.NOT_FOUND,reason="The movie service could not find the given movie")
    @ExceptionHandler(TitleNotFoundException.class)
    public void handleTitleNotFoundException(){}

    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="There has been a system error")
    @ExceptionHandler(TechnicalFailureException.class)
    public void handleTechnicalFailureException(){}

}