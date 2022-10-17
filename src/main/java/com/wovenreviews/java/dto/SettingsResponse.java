package com.wovenreviews.java.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SettingsResponse {

    private Boolean dailyEmailUpdates;

    public SettingsResponse() {
    }

    public SettingsResponse(Boolean dailyEmailUpdates) {
        this.dailyEmailUpdates = dailyEmailUpdates;
    }

    public Boolean getDailyEmailUpdates() {
        return dailyEmailUpdates;
    }

}
