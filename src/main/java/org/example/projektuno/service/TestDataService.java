package org.example.projektuno.service;

import org.example.projektuno.entity.*;
import org.example.projektuno.entity.playerChildClasses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class TestDataService {

    @Autowired private AppUserService userService;
    @Autowired private UserTeamService teamService;
    @Autowired private PlayerService playerService;
    @Autowired private MatchReportService matchReportService;

    public void setupTestData() {
        AppUser user = userService.findByUsername("testuser").orElse(null);
        if (user == null) {
            user = new AppUser("testuser", "test@example.com", "passwort123");
            user = userService.createUser(user);
            System.out.println("✅ User 'testuser' erstellt.");
        }

        UserTeam team = teamService.getTeamByUserId(user.getId()).orElse(null);
        if (team == null) {
            team = teamService.createTeamForUser("VolleyBangers", user);
            System.out.println("✅ Team 'VolleyBangers' erstellt.");
        }

        Player p1 = createOrLoadPlayer(new OutsideHitter("Max Attack", 50, 20, 10, 3));
        Player p2 = createOrLoadPlayer(new Setter("Sally Setter", 25, 100, 15, 5, 2));
        Player p3 = createOrLoadPlayer(new Libero("Lenny Libero", 80, 30, 5, 1));

        teamService.addPlayerToTeam(team.getId(), p1.getId());
        teamService.addPlayerToTeam(team.getId(), p2.getId());
        teamService.addPlayerToTeam(team.getId(), p3.getId());

        if (playerService.getAllPlayers().stream().noneMatch(p -> p.getName().equals("Freier Felix"))) {
            playerService.createPlayer(new OppositeHitter("Freier Felix", 10, 10, 10, 1));
            System.out.println("➕ Freier Spieler hinzugefügt.");
        }

        System.out.println("✅ Testdaten-Setup abgeschlossen.");

        // ⬇️ Spielbericht laden und verarbeiten
        String report = readReportFromFile();
        if (!report.isBlank()) {
            System.out.println("📄 Spielbericht geladen:");
            System.out.println(report);
            matchReportService.processMatchReport(report);
        } else {
            System.out.println("⚠️ Kein Spielbericht gefunden oder Datei leer.");
        }
    }

    private Player createOrLoadPlayer(Player player) {
        return playerService.getAllPlayers().stream()
            .filter(p -> p.getName().equals(player.getName()))
            .findFirst()
            .orElseGet(() -> playerService.createPlayer(player));
    }

    private String readReportFromFile() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("match_report.txt")) {
            if (input == null) {
                System.out.println("❌ Datei 'match_report.txt' nicht gefunden.");
                return "";
            }
            Scanner scanner = new Scanner(input, StandardCharsets.UTF_8);
            return scanner.useDelimiter("\\A").next(); // liest ganze Datei
        } catch (Exception e) {
            System.out.println("❌ Fehler beim Lesen von 'match_report.txt':");
            e.printStackTrace();
            return "";
        }
    }
}
