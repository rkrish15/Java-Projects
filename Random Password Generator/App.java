package org.example;

import java.security.SecureRandom;
import java.util.Scanner;

public class App {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Password Generator!");

            System.out.print("Enter the desired password length: ");
            int length = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Include uppercase letters? (yes/no): ");
            boolean includeUppercase = readYesNoInput(scanner);

            System.out.print("Include lowercase letters? (yes/no): ");
            boolean includeLowercase = readYesNoInput(scanner);

            System.out.print("Include digits? (yes/no): ");
            boolean includeDigits = readYesNoInput(scanner);

            System.out.print("Include special characters? (yes/no): ");
            boolean includeSpecial = readYesNoInput(scanner);

            System.out.print("Minimum number of uppercase letters: ");
            int minUppercase = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Minimum number of lowercase letters: ");
            int minLowercase = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Minimum number of digits: ");
            int minDigits = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Minimum number of special characters: ");
            int minSpecial = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (!includeUppercase && !includeLowercase && !includeDigits && !includeSpecial) {
                System.out.println("Error: At least one character type must be included.");
                return;
            }

            if (minUppercase + minLowercase + minDigits + minSpecial > length) {
                System.out.println("Error: The sum of minimum requirements exceeds the desired password length.");
                return;
            }

            String password = generateRandomPassword(length, includeUppercase, includeLowercase, includeDigits, includeSpecial, minUppercase, minLowercase, minDigits, minSpecial);
            System.out.println("Generated Password: " + password);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid options.");
        }
    }

    public static boolean readYesNoInput(Scanner scanner) {
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    public static String generateRandomPassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeDigits, boolean includeSpecial, int minUppercase, int minLowercase, int minDigits, int minSpecial) {
        StringBuilder characterSet = new StringBuilder();
        if (includeUppercase) characterSet.append(UPPERCASE);
        if (includeLowercase) characterSet.append(LOWERCASE);
        if (includeDigits) characterSet.append(DIGITS);
        if (includeSpecial) characterSet.append(SPECIAL_CHARACTERS);

        if (characterSet.length() == 0) {
            throw new IllegalArgumentException("No characters available to generate the password.");
        }

        StringBuilder password = new StringBuilder(length);

        // Add the minimum required characters
        for (int i = 0; i < minUppercase; i++) {
            password.append(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        }
        for (int i = 0; i < minLowercase; i++) {
            password.append(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        }
        for (int i = 0; i < minDigits; i++) {
            password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        for (int i = 0; i < minSpecial; i++) {
            password.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));
        }
        for (int i = password.length(); i < length; i++) {
            int index = RANDOM.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }

        // Shuffle the characters
        StringBuilder shuffledPassword = new StringBuilder(length);
        while (password.length() > 0) {
            int index = RANDOM.nextInt(password.length());
            shuffledPassword.append(password.charAt(index));
            password.deleteCharAt(index);
        }

        return shuffledPassword.toString();
    }
}
