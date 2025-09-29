package org.papelariapachecotorres.forecast;

import java.util.List;
import java.util.Map;

public record TrainRequest(
    List<Map<String, Object>> salesData,
    String modelType
) {}