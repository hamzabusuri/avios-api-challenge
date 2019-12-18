package com.service.parentalcontrol.hamza.controller;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;
import com.service.parentalcontrol.hamza.service.ParentalControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


//hosting the endpoint to connect to restful API service.
@RestController
@RequestMapping("/dynamoDb")
public class ParentalController {

    //inject parental control service into controller to use
    @Autowired
    ParentalControlService parentalControlService;

    @Autowired
    private DynamoDbRepository repository;

   /*@PostMapping
    public String insertIntoDynamoDB(@RequestBody MovieClassification movie) {
        repository.insertIntoDynamoDB(movie);
        return "Successfully inserted into DynamoDB table";
    }*/

    /*@GetMapping
    public ResponseEntity<MovieClassification> getMovieDetails(@RequestParam String movieId, @RequestParam String identifier) {
        MovieClassification movie = repository.getMovieDetails(movieId);
        return new ResponseEntity<MovieClassification>(movie, HttpStatus.OK);
    }*/




    @GetMapping("/permission/level/{pclPreference}/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Boolean checkParentalControlLevel(@PathVariable String movieId,
                                      @PathVariable String pclPreference) throws TitleNotFoundException, TechnicalFailureException {
        return parentalControlService.checkParentalControlLevel(movieId,pclPreference);
    }

    @GetMapping
    public String getMovieDetails(@RequestParam String movieId, @RequestParam String identifier) {
        MovieClassification movie = repository.getMovieDetails(movieId);
        return movie.getIdentifier();
    }


    //Throw TitleNotFoundException or TechnicalFailure exception for movies
    @ResponseStatus(value=HttpStatus.NOT_FOUND,reason="The movie service could not find the given movie")
    @ExceptionHandler(TitleNotFoundException.class)
    public void handleTitleNotFoundException(){}

    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="There has been a system error")
    @ExceptionHandler(TechnicalFailureException.class)
    public void handleTechnicalFailureException(){}

}