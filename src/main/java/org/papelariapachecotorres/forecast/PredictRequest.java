package org.papelariapachecotorres.forecast;

public record PredictRequest(
    int daysAhead,
    String modelType,
    double confidenceInterval
) {
    public PredictRequest {
        if (daysAhead <= 0) {
            throw new IllegalArgumentException("daysAhead must be positive");
        }
        if (confidenceInterval <= 0 || confidenceInterval >= 1) {
            throw new IllegalArgumentException("confidenceInterval must be between 0 and 1");
        }
    }
}