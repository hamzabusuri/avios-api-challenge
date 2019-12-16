package com.service.parentalcontrol.hamza;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.service.MovieService;
import com.service.parentalcontrol.hamza.service.ParentalControlServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = Application.class)
class ParentalControlServiceTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private ParentalControlServiceImpl parentalControlService;


    @Test
    public void isMoviePermissible_permissibleMovies_expectIsPermissible() throws TitleNotFoundException {

        when(movieService.getParentalControlLevel("The Jungle Book")).thenReturn("U");
        when(movieService.getParentalControlLevel("Home Alone")).thenReturn("PG");
        when(movieService.getParentalControlLevel("Junior")).thenReturn("12");

        assertTrue(parentalControlService.isMoviePermissible("U", "The Jungle Book"));
        assertTrue(parentalControlService.isMoviePermissible("PG", "Home Alone"));
        assertTrue(parentalControlService.isMoviePermissible("12", "Junior"));
    }

    @Test
    public void isMoviePermissible_impermissibleMovies_expectIsNotPermissible() throws TitleNotFoundException {

        when(movieService.getParentalControlLevel("Terminator 2: Judgement Day")).thenReturn("18");

        assertFalse(parentalControlService.isMoviePermissible("12", "Terminator 2: Judgement Day"));
    }

    @Test(expected = TitleNotFoundException.class)
    public void isMoviePermissible_movieNonExistent_expectTitleNotFoundException() throws TitleNotFoundException {

        when(movieService.getParentalControlLevel("The Rain")).thenThrow(TitleNotFoundException.class);

        parentalControlService.isMoviePermissible("15", "The Rain");
    }

    @Test(expected = TechnicalFailureException.class)
    public void isMoviePermissible_runtimeFailure_expectTechnicalFailureException() throws TitleNotFoundException {

        when(movieService.getParentalControlLevel("***")).thenThrow(TechnicalFailureException.class);

        parentalControlService.isMoviePermissible("U", "***");
    }
}