package com.service.parentalcontrol.hamza.controller;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.model.ParentalControlRating;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;
import com.service.parentalcontrol.hamza.service.ParentalControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



//hosting the endpoint to connect to restful API service.
@RestController
@RequestMapping("/dynamoDb")
public class ParentalController {

    //inject parental control service into controller to use
    @Autowired
    ParentalControlService parentalControlService;

    @Autowired
    private DynamoDbRepository repository;


    @GetMapping("/permission/level/{pclPreference}/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParentalControlRating checkParentalControlLevel(@PathVariable String movieId,
                                                           @PathVariable String pclPreference) throws TitleNotFoundException, TechnicalFailureException {
        boolean check = parentalControlService.checkParentalControlLevel(movieId,pclPreference);
        return ParentalControlRating.builder().movieId(movieId).canWatch(check).build();
    }

    //Throw TitleNotFoundException or TechnicalFailure exception for movies
    @ResponseStatus(value=HttpStatus.NOT_FOUND,reason="The movie service could not find the given movie")
    @ExceptionHandler(TitleNotFoundException.class)
    public void handleTitleNotFoundException(){}

    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="System error")
    @ExceptionHandler(TechnicalFailureException.class)
    public void handleTechnicalFailureException(){}

}