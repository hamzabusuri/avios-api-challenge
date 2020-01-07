package com.service.parentalcontrol.hamza.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ParentalControlRating {
    private String movieId;
    private boolean canWatch;
}