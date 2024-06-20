package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private static int currentQuestion = 0;
    private static int score = 0;

    private static final String[] questions = {
            "What is the capital of France?",
            "What is the largest planet in our solar system?",
            "What is the currency used in Japan?",
            "Which of these is a type of programming language?",
            "Who is the current president of the United States?",
            "What is the square root of 64?",
            "What element does 'O' represent on the periodic table?",
            "What is the longest river in the world?",
            "What year did the Titanic sink?",
            "What is the smallest prime number?",
            "What is the boiling point of water in Celsius?",
            "Which planet is known as the Red Planet?",
            "Who painted the Mona Lisa?",
            "What is the largest ocean on Earth?",
            "Which country is known as the Land of the Rising Sun?"
    };

    private static final String[][] options = {
            {"A. Paris", "B. London", "C. Berlin", "D. Madrid"},
            {"A. Earth", "B. Jupiter", "C. Saturn", "D. Venus"},
            {"A. Dollar", "B. Euro", "C. Yen", "D. Pound"},
            {"A. Banana", "B. Python", "C. Hamburger", "D. Pencil"},
            {"A. Donald Trump", "B. Joe Biden", "C. Barack Obama", "D. George W. Bush"},
            {"A. 6", "B. 7", "C. 8", "D. 9"},
            {"A. Oxygen", "B. Gold", "C. Silver", "D. Hydrogen"},
            {"A. Amazon", "B. Nile", "C. Yangtze", "D. Mississippi"},
            {"A. 1912", "B. 1913", "C. 1914", "D. 1915"},
            {"A. 0", "B. 1", "C. 2", "D. 3"},
            {"A. 80", "B. 90", "C. 100", "D. 120"},
            {"A. Mars", "B. Jupiter", "C. Saturn", "D. Mars"},
            {"A. Leonardo da Vinci", "B. Vincent van Gogh", "C. Pablo Picasso", "D. Michelangelo"},
            {"A. Pacific Ocean", "B. Atlantic Ocean", "C. Indian Ocean", "D. Arctic Ocean"},
            {"A. China", "B. Japan", "C. South Korea", "D. Vietnam"}
    };

    private static final int[] answers = {1, 2, 3, 2, 2, 3, 1, 2, 1, 3, 3, 1, 1, 1, 2}; // 1-based indexing

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Game");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel questionLabel = new JLabel(questions[currentQuestion]);
        questionLabel.setBounds(10, 20, 380, 25);
        panel.add(questionLabel);

        JRadioButton optionA = new JRadioButton(options[currentQuestion][0]);
        optionA.setBounds(10, 50, 380, 25);
        panel.add(optionA);

        JRadioButton optionB = new JRadioButton(options[currentQuestion][1]);
        optionB.setBounds(10, 80, 380, 25);
        panel.add(optionB);

        JRadioButton optionC = new JRadioButton(options[currentQuestion][2]);
        optionC.setBounds(10, 110, 380, 25);
        panel.add(optionC);

        JRadioButton optionD = new JRadioButton(options[currentQuestion][3]);
        optionD.setBounds(10, 140, 380, 25);
        panel.add(optionD);

        ButtonGroup group = new ButtonGroup();
        group.add(optionA);
        group.add(optionB);
        group.add(optionC);
        group.add(optionD);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(150, 200, 80, 25);
        panel.add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedAnswer = -1;
                if (optionA.isSelected()) {
                    selectedAnswer = 1;
                } else if (optionB.isSelected()) {
                    selectedAnswer = 2;
                } else if (optionC.isSelected()) {
                    selectedAnswer = 3;
                } else if (optionD.isSelected()) {
                    selectedAnswer = 4;
                }

                if (selectedAnswer == answers[currentQuestion]) {
                    score++;
                }

                currentQuestion++;
                if (currentQuestion < questions.length) {
                    questionLabel.setText(questions[currentQuestion]);
                    optionA.setText(options[currentQuestion][0]);
                    optionB.setText(options[currentQuestion][1]);
                    optionC.setText(options[currentQuestion][2]);
                    optionD.setText(options[currentQuestion][3]);
                    group.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(panel, "Quiz Over! Your score is: " + score + "/" + questions.length);
                    System.exit(0);
                }
            }
        });
    }
}
