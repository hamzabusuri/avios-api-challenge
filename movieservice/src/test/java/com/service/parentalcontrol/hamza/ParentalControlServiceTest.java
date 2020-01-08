package com.service.parentalcontrol.hamza;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import com.service.parentalcontrol.hamza.impl.ParentalControlServiceImpl;
import com.service.parentalcontrol.hamza.model.MovieClassification;
import com.service.parentalcontrol.hamza.repository.DynamoDbRepository;
import com.service.parentalcontrol.hamza.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest()
public class ParentalControlServiceTest {

    @Mock
    private MovieService movieService;

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private DynamoDbRepository repo;

    @InjectMocks
    private ParentalControlServiceImpl pcs = new ParentalControlServiceImpl();

    @Test
    public void contextLoads() {
    }


    @Before
    public void setup() throws TechnicalFailureException, TitleNotFoundException {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCanWatch() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);

        when(repo.getMovieDetails(movieId)).thenReturn(true);

        assertTrue(pcs.checkParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void testCanWatchPG() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("PG").build();

        String movieId = movie.getMovieId();
        String userLevel = "PG";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);

        when(repo.getMovieDetails(movieId)).thenReturn(true);

        assertTrue(pcs.checkParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void testCanWatch12() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("12").build();

        String movieId = movie.getMovieId();
        String userLevel = "12";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);

        when(repo.getMovieDetails(movieId)).thenReturn(true);

        assertTrue(pcs.checkParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void testCanWatch15() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("15").build();

        String movieId = movie.getMovieId();
        String userLevel = "15";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);

        when(repo.getMovieDetails(movieId)).thenReturn(true);

        assertTrue(pcs.checkParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void testCanWatch18() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("18").build();

        String movieId = movie.getMovieId();
        String userLevel = "18";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);

        when(repo.getMovieDetails(movieId)).thenReturn(true);

        assertTrue(pcs.checkParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void testCannotWatch() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("2").identifier("PG").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);

        when(repo.getMovieDetails(movieId)).thenReturn(true);

        assertFalse(pcs.checkParentalControlLevel(movieId, userLevel));

    }

    @Test(expected=TitleNotFoundException.class)
    public void testMovieNotExist() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("6").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "U";
        String movieLevel = movie.getIdentifier();

        when(repo.getMovieDetails(movieId)).thenThrow(TitleNotFoundException.class);

        pcs.checkParentalControlLevel(movieId, userLevel);

    }

   @Test(expected=TechnicalFailureException.class)
    public void testSystemError() throws Exception {
        MovieClassification movie = MovieClassification.builder().movieId("1").identifier("U").build();

        String movieId = movie.getMovieId();
        String userLevel = "***";
        String movieLevel = movie.getIdentifier();

        when(repo.getMovieDetails(movieId)).thenReturn(true);

        when(movieService.getParentalControlLevel(movieId)).thenThrow(TechnicalFailureException.class);
        pcs.checkParentalControlLevel(movieId, userLevel);

    }


}