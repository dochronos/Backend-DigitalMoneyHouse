package com.example.accounts_server.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class AccountUtils {

    private final Random random = new Random();

    public String generateRandomCVU() {
        String cvu = String.format("%022d", Math.abs(random.nextLong()));
        System.out.println("Generated CVU: " + cvu);
        return cvu;
    }

    public String generateAlias() {
        List<String> words = loadWordsFromFile();
        if (words.size() < 3) {
            throw new RuntimeException("Not enough words in the file to generate an alias");
        }

        return randomWords(words, 3).stream().collect(Collectors.joining("."));
    }

    private List<String> loadWordsFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("wordsAlias.txt").getInputStream()))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error loading words file", e);
        }
    }

    private List<String> randomWords(List<String> words, int count) {
        if (count > words.size()) {
            throw new IllegalArgumentException("Count exceeds number of available words");
        }

        return random.ints(0, words.size())
                .distinct()
                .limit(count)
                .mapToObj(words::get)
                .collect(Collectors.toList());
    }
}