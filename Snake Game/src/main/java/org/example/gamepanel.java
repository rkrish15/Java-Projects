package org.example;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class gamepanel extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image apple;
    private Image headUp, headDown, headLeft, headRight;
    private Image tailUp, tailDown, tailLeft, tailRight;
    private Image vertical, horizontal;
    private Image topLeft, topRight, bottomLeft, bottomRight;

    public gamepanel() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        ImageIcon appleIcon = new ImageIcon(getClass().getResource("/apple.png"));
        apple = appleIcon.getImage();

        headUp = new ImageIcon(getClass().getResource("/headup.png")).getImage();
        headDown = new ImageIcon(getClass().getResource("/headdown.png")).getImage();
        headLeft = new ImageIcon(getClass().getResource("/headleft.png")).getImage();
        headRight = new ImageIcon(getClass().getResource("/headright.png")).getImage();

        tailUp = new ImageIcon(getClass().getResource("/tailup.png")).getImage();
        tailDown = new ImageIcon(getClass().getResource("/taildown.png")).getImage();
        tailLeft = new ImageIcon(getClass().getResource("/tailleft.png")).getImage();
        tailRight = new ImageIcon(getClass().getResource("/tailright.png")).getImage();

        vertical = new ImageIcon(getClass().getResource("/vertical.png")).getImage();
        horizontal = new ImageIcon(getClass().getResource("/horizontal.png")).getImage();

        topLeft = new ImageIcon(getClass().getResource("/topleft.png")).getImage();
        topRight = new ImageIcon(getClass().getResource("/topright.png")).getImage();
        bottomLeft = new ImageIcon(getClass().getResource("/bottomleft.png")).getImage();
        bottomRight = new ImageIcon(getClass().getResource("/bottomright.png")).getImage();
    }

    private void initGame() {
        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    if (upDirection) {
                        g.drawImage(headUp, x[z], y[z], this);
                    } else if (downDirection) {
                        g.drawImage(headDown, x[z], y[z], this);
                    } else if (leftDirection) {
                        g.drawImage(headLeft, x[z], y[z], this);
                    } else {
                        g.drawImage(headRight, x[z], y[z], this);
                    }
                } else if (z == dots - 1) {
                    if (x[z] == x[z - 1]) {
                        if (y[z] < y[z - 1]) {
                            g.drawImage(tailUp, x[z], y[z], this);
                        } else {
                            g.drawImage(tailDown, x[z], y[z], this);
                        }
                    } else {
                        if (x[z] < x[z - 1]) {
                            g.drawImage(tailLeft, x[z], y[z], this);
                        } else {
                            g.drawImage(tailRight, x[z], y[z], this);
                        }
                    }
                } else {
                    if (x[z] == x[z - 1] && x[z] == x[z + 1]) {
                        g.drawImage(vertical, x[z], y[z], this);
                    } else if (y[z] == y[z - 1] && y[z] == y[z + 1]) {
                        g.drawImage(horizontal, x[z], y[z], this);
                    } else if ((x[z - 1] < x[z] && y[z + 1] < y[z]) || (x[z + 1] < x[z] && y[z - 1] < y[z])) {
                        g.drawImage(topRight, x[z], y[z], this);
                    } else if ((x[z - 1] > x[z] && y[z + 1] < y[z]) || (x[z + 1] > x[z] && y[z - 1] < y[z])) {
                        g.drawImage(topLeft, x[z], y[z], this);
                    } else if ((x[z - 1] < x[z] && y[z + 1] > y[z]) || (x[z + 1] < x[z] && y[z - 1] > y[z])) {
                        g.drawImage(bottomRight, x[z], y[z], this);
                    } else if ((x[z - 1] > x[z] && y[z + 1] > y[z]) || (x[z + 1] > x[z] && y[z - 1] > y[z])) {
                        g.drawImage(bottomLeft, x[z], y[z], this);
                    }
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
}
