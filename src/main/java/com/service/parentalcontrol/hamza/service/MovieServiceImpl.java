package com.service.parentalcontrol.hamza.service;

import com.service.parentalcontrol.hamza.exception.TechnicalFailureException;
import com.service.parentalcontrol.hamza.exception.TitleNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.service.parentalcontrol.hamza.model.ParentalControlLevel;

/**
 * This stubbed implementation is for purposes of demonstrating application functionality.
 */
@Component
public class MovieServiceImpl implements MovieService {

	@Override
	public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		String result = "";

		if (!StringUtils.isEmpty(movieId)) {
			int movie = Integer.parseInt(movieId);

			switch (movie) {
				case 1:
					result = ParentalControlLevel.UNIVERSAL.getLevel();
					break;
				case 2:
					result = ParentalControlLevel.PARENTAL_GUIDANCE.getLevel();
					break;
				case 3:
					result = ParentalControlLevel.TWELVE.getLevel();
					break;
				case 4:
					result = ParentalControlLevel.FIFTEEN.getLevel();
					break;
				case 5:
					result = ParentalControlLevel.EIGHTEEN.getLevel();
					break;
				default:
					throw new TitleNotFoundException("The movie service could not find the given movie.");
			}
		} else {
			throw new TitleNotFoundException("The movie service could not find the given movie.");
		}

		return result;
	}

}