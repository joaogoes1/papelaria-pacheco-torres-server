package org.papelariapachecotorres.forecast;

import org.papelariapachecotorres.vendas.VendaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ForecastService {

    @Value("${ml.service.url:http://ml-service:5000}")
    private String mlServiceUrl;

    private final VendaService vendaService;
    private final RestTemplate restTemplate;

    public ForecastService(VendaService vendaService) {
        this.vendaService = vendaService;
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> trainModel(TrainRequest request) {
        String url = mlServiceUrl + "/api/forecast/train";

        Map<String, Object> body = new HashMap<>();
        body.put("sales_data", request.salesData());
        body.put("model_type", request.modelType());

        return restTemplate.postForObject(url, body, Map.class);
    }

    public Map<String, Object> predict(PredictRequest request) {
        String url = mlServiceUrl + "/api/forecast/predict";

        Map<String, Object> body = new HashMap<>();
        body.put("days_ahead", request.daysAhead());
        body.put("model_type", request.modelType());
        body.put("confidence_interval", request.confidenceInterval());

        return restTemplate.postForObject(url, body, Map.class);
    }

    public Map<String, Object> evaluate(EvaluateRequest request) {
        String url = mlServiceUrl + "/api/forecast/evaluate";

        Map<String, Object> body = new HashMap<>();
        body.put("sales_data", request.salesData());
        body.put("test_size", request.testSize());
        body.put("model_type", request.modelType());

        return restTemplate.postForObject(url, body, Map.class);
    }

    public Map<String, Object> compareModels(CompareRequest request) {
        String url = mlServiceUrl + "/api/forecast/compare-models";

        Map<String, Object> body = new HashMap<>();
        body.put("sales_data", request.salesData());
        body.put("models", request.models());

        return restTemplate.postForObject(url, body, Map.class);
    }

    /**
     * Auto-forecast: Automatically trains model with all available data and generates predictions
     */
    public Map<String, Object> autoForecast(int daysAhead, String modelType) {
        // 1. Get all sales data
        var vendas = vendaService.getAll();

        // 2. Group by date and sum totals
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        Map<String, Double> dailySales = new HashMap<>();

        for (var venda : vendas) {
            // Convert Instant to LocalDate
            LocalDate date = venda.getData()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
            String formattedDate = date.format(formatter);

            dailySales.merge(formattedDate, venda.getTotal().doubleValue(), Double::sum);
        }

        // 3. Convert to list format for ML service
        List<Map<String, Object>> salesData = dailySales.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    Map<String, Object> dataPoint = new HashMap<>();
                    dataPoint.put("date", entry.getKey());
                    dataPoint.put("total", entry.getValue());
                    return dataPoint;
                })
                .collect(Collectors.toList());

        // 4. Check if we have enough data
        if (salesData.size() < 14) {
            throw new RuntimeException(
                    "Insufficient data for forecasting. Need at least 14 days, but have " + salesData.size()
            );
        }

        // 5. Train model
        String trainUrl = mlServiceUrl + "/api/forecast/train";
        Map<String, Object> trainBody = new HashMap<>();
        trainBody.put("sales_data", salesData);
        trainBody.put("model_type", modelType);

        Map<String, Object> trainResult = restTemplate.postForObject(trainUrl, trainBody, Map.class);

        // 6. Generate predictions
        String predictUrl = mlServiceUrl + "/api/forecast/predict";
        Map<String, Object> predictBody = new HashMap<>();
        predictBody.put("days_ahead", daysAhead);
        predictBody.put("model_type", modelType);
        predictBody.put("confidence_interval", 0.95);

        Map<String, Object> predictResult = restTemplate.postForObject(predictUrl, predictBody, Map.class);

        // 7. Combine results
        Map<String, Object> result = new HashMap<>();
        result.put("training", trainResult);
        result.put("forecast", predictResult);
        result.put("historical_data_points", salesData.size());
        result.put("model_type", modelType);

        return result;
    }
}