package com.service.parentalcontrol.hamza;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.service.MovieService;
import com.service.parentalcontrol.hamza.service.ParentalControlServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created by Aamirio on 17/06/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private ParentalControlServiceImpl parentalControlService;


    @Test
    public void isMoviePermissible_permissibleMovies_expectIsPermissible() throws TitleNotFoundException, TechnicalFailureException {

        when(movieService.getParentalControlLevel("The Jungle Book")).thenReturn("U");
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
    }

    @Test(expected = TitleNotFoundException.class)
    public void isMoviePermissible_movieNonExistent_expectTitleNotFoundException() throws TitleNotFoundException, TechnicalFailureException {

        when(movieService.getParentalControlLevel("The Rain")).thenThrow(TitleNotFoundException.class);

        parentalControlService.checkParentalControlLevel("15", "The Rain");
    }

    @Test(expected = TechnicalFailureException.class)
    public void isMoviePermissible_runtimeFailure_expectTechnicalFailureException() throws TechnicalFailureException, TitleNotFoundException {

        when(movieService.getParentalControlLevel("***")).thenThrow(TechnicalFailureException.class);

        parentalControlService.checkParentalControlLevel("U", "***");
    }
}