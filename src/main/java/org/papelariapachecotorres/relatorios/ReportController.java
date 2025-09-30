package org.papelariapachecotorres.relatorios;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/relatorios")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/vendas/exportar")
    public ResponseEntity<String> exportVendas() {
        try {
            String csvContent = reportService.exportVendasCsv();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"vendas.csv\"")
                    .body(csvContent);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/clientes/exportar")
    public ResponseEntity<String> exportClientes() {
        try {
            String csvContent = reportService.exportClientesCsv();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"clientes.csv\"")
                    .body(csvContent);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/estoque/exportar")
    public ResponseEntity<String> exportEstoque() {
        try {
            String csvContent = reportService.exportEstoqueCsv();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"estoque.csv\"")
                    .body(csvContent);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
} 