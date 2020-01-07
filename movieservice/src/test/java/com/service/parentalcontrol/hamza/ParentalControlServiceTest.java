package com.service.parentalcontrol.hamza;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.impl.ParentalControlServiceImpl;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.service.MovieService;
import com.service.parentalcontrol.hamza.service.ParentalControlService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
public class ParentalControlServiceTest {

    private MovieService movieService;

    private ParentalControlService pcs;

    @InjectMocks
    private ParentalControlServiceImpl impl;

    @Test
    public void contextLoads() {
    }


    @Before
    public void setup() throws TechnicalFailureException, TitleNotFoundException {
        movieService = mock(MovieService.class, RETURNS_SMART_NULLS);
        pcs = mock(ParentalControlService.class, RETURNS_SMART_NULLS);
    }

    @Test
    public void testCanWatch() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        when(pcs.checkParentalControlLevel(movieId, userLevel)).thenReturn(true);
        assertTrue(pcs.checkParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void testCannotWatch() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("2").identifier("PG").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        when(pcs.checkParentalControlLevel(movieId, userLevel)).thenReturn(false);
        assertFalse(pcs.checkParentalControlLevel(movieId, userLevel));

    }

    @Test(expected=TitleNotFoundException.class)
    public void testMovieNotExist() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("6").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        when(pcs.checkParentalControlLevel(movieId, userLevel)).thenThrow(TitleNotFoundException.class);
        pcs.checkParentalControlLevel(movieId, userLevel);

    }

    @Test(expected=TechnicalFailureException.class)
    public void testSystemError() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "***";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        when(pcs.checkParentalControlLevel(movieId, userLevel)).thenThrow(TechnicalFailureException.class);
        pcs.checkParentalControlLevel(movieId, userLevel);

    }


}