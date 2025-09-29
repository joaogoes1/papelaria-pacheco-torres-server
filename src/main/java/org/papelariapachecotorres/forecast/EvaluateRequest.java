package org.papelariapachecotorres.forecast;

import java.util.List;
import java.util.Map;

public record EvaluateRequest(
    List<Map<String, Object>> salesData,
    double testSize,
    String modelType
) {}