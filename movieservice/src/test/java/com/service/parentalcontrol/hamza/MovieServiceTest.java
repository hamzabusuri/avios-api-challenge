package com.service.parentalcontrol.hamza;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.impl.MovieServiceImpl;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
public class MovieServiceTest {

    private MovieServiceImpl movieService;

    @Test
    public void contextLoads() {
    }


    @Before
    public void setup() throws TechnicalFailureException, TitleNotFoundException {
        movieService = mock(MovieServiceImpl.class, RETURNS_SMART_NULLS);
    }

    @Test
    public void testCorrectParentalControlLevel() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        assertThat(movieService.getParentalControlLevel(movieId).equals("U"));
    }

    @Test(expected = TitleNotFoundException.class)
    public void testWrongParentalControlLevel() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("6").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenThrow(TitleNotFoundException.class);
        assertThat(movieService.getParentalControlLevel(movieId).equals("U"));
    }


}