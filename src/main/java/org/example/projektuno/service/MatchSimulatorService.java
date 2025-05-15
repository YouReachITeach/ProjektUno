package org.example.projektuno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchSimulatorService {

    @Autowired
    private MatchReportService matchReportService;

    public void simulateExampleMatch() {
        String reportText = """
            Max Attack macht einen Punkt.
            Sally Setter mit einem Assist.
            Max Attack blockt einen Ball.
            Lenny Libero serviert ins Netz.
        """;

        matchReportService.processMatchReport(reportText);
        System.out.println("✅ Spielsimulation abgeschlossen.");
    }
}
