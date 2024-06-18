package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testGenerateRandomPassword() {
        int length = 12;
        String password = App.generateRandomPassword(length, true, true, true, true, 1, 1, 1, 1);
        assertEquals(length, password.length());

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            if (Character.isLowerCase(c)) hasLowercase = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*()-_+=<>?".indexOf(c) >= 0) hasSpecial = true;
        }

        assertTrue(hasUppercase);
        assertTrue(hasLowercase);
        assertTrue(hasDigit);
        assertTrue(hasSpecial);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateRandomPasswordNoCharacters() {
        App.generateRandomPassword(12, false, false, false, false, 0, 0, 0, 0);
    }

    @Test
    public void testGenerateRandomPasswordWithDifferentConfigurations() {
        int length = 16;

        String passwordUppercase = App.generateRandomPassword(length, true, false, false, false, 2, 0, 0, 0);
        assertTrue(passwordUppercase.matches("[A-Z]+"));

        String passwordLowercase = App.generateRandomPassword(length, false, true, false, false, 0, 2, 0, 0);
        assertTrue(passwordLowercase.matches("[a-z]+"));

        String passwordDigits = App.generateRandomPassword(length, false, false, true, false, 0, 0, 2, 0);
        assertTrue(passwordDigits.matches("[0-9]+"));

        String passwordSpecial = App.generateRandomPassword(length, false, false, false, true, 0, 0, 0, 2);
        assertTrue(passwordSpecial.matches("[!@#$%^&*()\\-_=+<>?]+"));
    }

    @Test
    public void testMinimumRequirements() {
        int length = 20;
        int minUppercase = 5;
        int minLowercase = 5;
        int minDigits = 5;
        int minSpecial = 5;

        String password = App.generateRandomPassword(length, true, true, true, true, minUppercase, minLowercase, minDigits, minSpecial);

        int actualUppercase = 0;
        int actualLowercase = 0;
        int actualDigits = 0;
        int actualSpecial = 0;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) actualUppercase++;
            if (Character.isLowerCase(c)) actualLowercase++;
            if (Character.isDigit(c)) actualDigits++;
            if ("!@#$%^&*()-_+=<>?".indexOf(c) >= 0) actualSpecial++;
        }

        assertTrue(actualUppercase >= minUppercase);
        assertTrue(actualLowercase >= minLowercase);
        assertTrue(actualDigits >= minDigits);
        assertTrue(actualSpecial >= minSpecial);
    }
}
