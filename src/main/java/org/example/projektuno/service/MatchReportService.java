package org.example.projektuno.service;

import org.example.projektuno.entity.MatchReport;
import org.example.projektuno.entity.Player;
import org.example.projektuno.repositories.MatchReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchReportService {

    @Autowired
    private MatchReportRepository matchReportRepository;

    @Autowired
    private PlayerService playerService;

    public MatchReport submitReport(String text) {
        MatchReport report = new MatchReport(text);
        return matchReportRepository.save(report);
    }

    public Map<String, String> processMatchReport(String reportText) {
        List<Player> allPlayers = playerService.getAllPlayers();
        Map<String, String> logMap = new LinkedHashMap<>();

        for (Player player : allPlayers) {
            String name = player.getName();

            if (reportText.contains(name)) {
                int addedPoints = calculatePoints(reportText, name);
                int newPoints = player.getPoints() + addedPoints;
                player.setPoints(newPoints);
                playerService.createPlayer(player); // Save updated player
                String log = "➡️ " + name + " bekommt +" + addedPoints + " Punkte (gesamt: " + newPoints + ")";
                logMap.put(name, log);
                System.out.println(log);
            }
        }

        return logMap;
    }

    private int calculatePoints(String text, String playerName) {
        int points = 0;
        String sentence = extractSentenceWithName(text, playerName).toLowerCase();

        if (sentence.contains("macht einen punkt")) points += 3;
        if (sentence.contains("spielt den ball")) points += 1;
        if (sentence.contains("rettet")) points += 2;
        if (sentence.contains("blockt")) points += 2;
        if (sentence.contains("assistiert") || sentence.contains("assist")) points += 2;
        if (sentence.contains("fehler")) points -= 1;

        return points;
    }

    private String extractSentenceWithName(String text, String name) {
        String[] sentences = text.split("\\.");
        for (String sentence : sentences) {
            if (sentence.contains(name)) return sentence.trim();
        }
        return "";
    }
}
