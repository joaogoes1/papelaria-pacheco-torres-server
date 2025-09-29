package org.papelariapachecotorres.forecast;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @PostMapping("/train")
    public ResponseEntity<?> trainModel(@RequestBody TrainRequest request) {
        try {
            var result = forecastService.trainModel(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody PredictRequest request) {
        try {
            var result = forecastService.predict(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/evaluate")
    public ResponseEntity<?> evaluate(@RequestBody EvaluateRequest request) {
        try {
            var result = forecastService.evaluate(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/compare")
    public ResponseEntity<?> compareModels(@RequestBody CompareRequest request) {
        try {
            var result = forecastService.compareModels(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/auto-forecast")
    public ResponseEntity<?> autoForecast(
            @RequestParam(defaultValue = "30") int daysAhead,
            @RequestParam(defaultValue = "prophet") String modelType
    ) {
        try {
            var result = forecastService.autoForecast(daysAhead, modelType);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    record ErrorResponse(String error) {}
}