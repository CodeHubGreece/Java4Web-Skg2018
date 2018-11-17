package org.regeneration.exceptions;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String error;
    private String details;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ErrorDetails(Date timestamp, String error, String details) {
        this.timestamp = timestamp;
        this.error = error;
        this.details = details;
    }
}
