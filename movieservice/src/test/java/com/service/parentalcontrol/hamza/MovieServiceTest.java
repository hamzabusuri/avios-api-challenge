package com.service.parentalcontrol.hamza;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.impl.MovieServiceImpl;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;
import com.service.parentalcontrol.hamza.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest()
public class MovieServiceTest {

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private DynamoDbRepository repo;

    @Mock
    private MovieServiceImpl movieService = new MovieServiceImpl();

    @Test
    public void contextLoads() {
    }


    @Before
    public void setup() throws TechnicalFailureException, TitleNotFoundException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validParentalControl() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("U").build();

        String movieId = movie.getMovieId();
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);

        assertTrue(movieService.getParentalControlLevel(movieId).equals("U"));
    }

    @Test
    public void invalidParentalControl() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("PG").build();

        String movieId = movie.getMovieId();
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        assertFalse(movieService.getParentalControlLevel(movieId).equals("U"));
    }

    @Test(expected = TitleNotFoundException.class)
    public void testMovieNotExists() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("6").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();


        when(movieService.getParentalControlLevel(movieId)).thenThrow(TitleNotFoundException.class);
        assertThat(movieService.getParentalControlLevel(movieId).equals("U"));
    }


}