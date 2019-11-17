package com.example.EScorerServer.errors;

public class SummaryNotFoundException extends RuntimeException {
    public SummaryNotFoundException(int summaryId) {
        super("Summary not found " + summaryId);
    }
}
