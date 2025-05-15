package org.example.projektuno.entity;

import jakarta.persistence.*;

@Entity
public class MatchReport {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String reportText;

    private boolean processed = false;

    public MatchReport() {}

    public MatchReport(String reportText) {
        this.reportText = reportText;
    }

    public Long getId() {
        return id;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
