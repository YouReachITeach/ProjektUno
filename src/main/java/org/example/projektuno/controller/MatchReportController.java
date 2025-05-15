package org.example.projektuno.controller;

import org.example.projektuno.entity.MatchReport;
import org.example.projektuno.service.MatchReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/match")
public class MatchReportController {

    @Autowired
    private MatchReportService analysisService;

    @PostMapping("/submit")
    public ResponseEntity<MatchReport> submitReport(@RequestBody String text) {
        MatchReport saved = analysisService.submitReport(text);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, String>> analyze(@RequestBody String reportText) {
        return ResponseEntity.ok(analysisService.processMatchReport(reportText));
    }
}
