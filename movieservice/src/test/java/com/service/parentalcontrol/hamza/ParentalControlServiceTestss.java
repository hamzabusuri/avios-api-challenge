package com.service.parentalcontrol.hamza;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;
import com.service.parentalcontrol.hamza.service.MovieService;
import com.service.parentalcontrol.hamza.impl.ParentalControlServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTestss {

    @Mock
    private MovieService movieService;

    @Mock
    private DynamoDbRepository repo;

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @InjectMocks
    private ParentalControlServiceImpl parentalControlService;


   /*@Test
    public void isMoviePermissible_permissibleMovies_expectIsPermissible() throws TitleNotFoundException, TechnicalFailureException {

        when(movieService.getParentalControlLevel("The Jungle Book")).thenReturn(movie.getIdentifier());
        when(movieService.getParentalControlLevel("Home Alone")).thenReturn("PG");
        when(movieService.getParentalControlLevel("Junior")).thenReturn("12");

        assertTrue(parentalControlService.checkParentalControlLevel("U", "The Jungle Book"));
        assertTrue(parentalControlService.checkParentalControlLevel("PG", "Home Alone"));
        assertTrue(parentalControlService.checkParentalControlLevel("12", "Junior"));
    }

    @Test
    public void isMoviePermissible_impermissibleMovies_expectIsNotPermissible() throws TitleNotFoundException, TechnicalFailureException {

        when(movieService.getParentalControlLevel("Terminator 2: Judgement Day")).thenReturn("18");

        assertFalse(parentalControlService.checkParentalControlLevel("12", "Terminator 2: Judgement Day"));
    }*/

    /*@Test(expected = TitleNotFoundException.class)
    public void isMoviePermissible_movieNonExistent_expectTitleNotFoundException() throws TitleNotFoundException, TechnicalFailureException {

        MovieClassification.MovieClassificationBuilder movie = MovieClassification.builder().movieId(MOVIE_ID).identifier("U");

        Mockito.when(movieService.getParentalControlLevel("6")).thenThrow(TitleNotFoundException.class);

        parentalControlService.checkParentalControlLevel("6", "U");
    }

    @Test(expected = TechnicalFailureException.class)
    public void isMoviePermissible_runtimeFailure_expectTechnicalFailureException() throws TechnicalFailureException, TitleNotFoundException {
        Mockito.when(movieService.getParentalControlLevel("1")).thenThrow(TechnicalFailureException.class);

        parentalControlService.checkParentalControlLevel("1", "***");
    }*/

}