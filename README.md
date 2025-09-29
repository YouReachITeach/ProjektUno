Bisher ist die Basic Layered Architecture in der Version 2.1.0

Fehlen tut noch

- Security(JWT Token)
- Logging
- Error Handling
- Testing
- MatchReportController, Service, Repo alle überarbeiten + CRUD
- TestDataService
- Momentan muss man spieler manuell in eine Liga adden (kein endpoint), eigentlich sollte league beim erstellen direkt
  set aller aktuellen spieler in die Map bekommen.
  -> Workflow: League Manuell erstellen, dann UserTeams die Spieler kaufen und verkaufen lassen.
- Unterscheiden zwischen spielern im generellen UserTeam und spielern die für ein spiel Aufgestellt sind

- den spielertyp rausfinden geht gerade mit komischen java workarounds (siehe klasse player), eigentlich gibt es spalte
  in der db dafür.


- einpflegen von spielern in datenbank: erst Spierler erstellen und speichern, dann mit spielerID in die ligen
  hinzufügen.