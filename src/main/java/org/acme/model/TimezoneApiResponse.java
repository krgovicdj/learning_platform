package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class TimezoneApiResponse {

    @JsonProperty("timeZone")
    private String timeZone;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    // Getteri i setteri
    public String getTimeZone() { return timeZone; }
    public void setTimeZone(String timeZone) { this.timeZone = timeZone; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
}
