package com.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    // You can define any setup and teardown methods here if needed

    @BeforeClass
    public static void setUp() {
        // Setup tasks before running the tests
    }

    @AfterClass
    public static void tearDown() {
        // Cleanup tasks after running all tests
    }

    @Test
    public void testCalculateScoreCorrectAnswer() {
        // Example test for score calculation
        int[] answers = {1, 2, 3, 2, 2, 3, 1, 2, 1, 3}; // Mock answers
        int[] userResponses = {1, 2, 3, 2, 2, 3, 1, 2, 1, 3}; // Simulate correct responses

        int expectedScore = answers.length; // All correct answers
        int actualScore = calculateScore(answers, userResponses);

        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testCalculateScoreIncorrectAnswer() {
        // Example test for score calculation with some incorrect answers
        int[] answers = {1, 2, 3, 2, 2, 3, 1, 2, 1, 3}; // Mock answers
        int[] userResponses = {1, 2, 3, 1, 2, 3, 1, 2, 4, 3}; // Simulate some incorrect responses

        int expectedScore = 8; // 8 correct answers out of 10
        int actualScore = calculateScore(answers, userResponses);

        assertEquals(expectedScore, actualScore);
    }

    // Utility method to calculate the score
    private int calculateScore(int[] answers, int[] userResponses) {
        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == userResponses[i]) {
                score++;
            }
        }
        return score;
    }
}

