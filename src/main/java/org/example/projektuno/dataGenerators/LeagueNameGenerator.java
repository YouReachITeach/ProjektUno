package org.example.projektuno.dataGenerators;

import java.util.Random;

public class LeagueNameGenerator {
    private static final String[] ADJECTIVES = {"Epic", "Legendary", "Mighty", "Fierce", "Brave", "Noble", "Swift"};
    private static final String[] NOUNS = {"Warriors", "Champions", "Titans", "Gladiators", "Knights", "Rangers", "Defenders"};

    public static String generateRandomLeagueName() {
        Random random = new Random();
        String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[random.nextInt(NOUNS.length)];
        return adjective + " " + noun;
    }
}