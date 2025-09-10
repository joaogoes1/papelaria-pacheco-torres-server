package org.papelariapachecotorres.relatorios;

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
            String filePath = reportService.exportVendasCsv();
            return ResponseEntity.ok("Relatório de vendas exportado para: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao exportar relatório: " + e.getMessage());
        }
    }

    @GetMapping("/clientes/exportar")
    public ResponseEntity<String> exportClientes() {
        try {
            String filePath = reportService.exportClientesCsv();
            return ResponseEntity.ok("Relatório de clientes exportado para: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao exportar relatório: " + e.getMessage());
        }
    }

    @GetMapping("/estoque/exportar")
    public ResponseEntity<String> exportEstoque() {
        try {
            String filePath = reportService.exportEstoqueCsv();
            return ResponseEntity.ok("Relatório de estoque exportado para: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao exportar relatório: " + e.getMessage());
        }
    }
} 