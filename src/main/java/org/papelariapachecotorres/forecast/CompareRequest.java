package org.papelariapachecotorres.forecast;

import java.util.List;
import java.util.Map;

public record CompareRequest(
    List<Map<String, Object>> salesData,
    List<String> models
) {}