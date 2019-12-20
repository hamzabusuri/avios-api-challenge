package com.service.parentalcontrol.hamza.model;

import lombok.*;

@Builder
@Value
public class ParentalControlRating {
    private String movieId;
    private boolean canWatch;
}