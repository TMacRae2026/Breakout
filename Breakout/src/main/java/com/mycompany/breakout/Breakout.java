/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.breakout;

import com.mycompany.breakout.upgrade.Upgrade;
import com.mycompany.breakout.upgrade.impl.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author TMacRae2026
 */
public class Breakout {
    
    static Paddle paddle = new Paddle(350, 500);
    
    //init input variables
    static boolean leftPressed = false;
    static boolean rightPressed = false;
    
    static ArrayList<Brick> bricks = new ArrayList<>();
    public static ArrayList<Ball> balls = new ArrayList<>();
    public static ArrayList<Upgrade> upgrades = new ArrayList<>();
    
    public static void main(String[] args) {
        
        //test upgrade
        upgrades.add(new Multiply(300, 600));
        
        int rows = 20;
        int cols = 40;
        int screenWidth = 800;
        int brickWidth = (screenWidth - (cols-1)) / cols ;
        int brickHeight = 10;
        
        Color[] rowColors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
            Color.CYAN, Color.MAGENTA, Color.PINK
        };
        
        for (int row = 0; row < rows; row++) { // Loop through rows
            Color rowColor = rowColors[row % rowColors.length];
            
            for (int col = 0; col < cols; col++) { // Loop through columns
                int x = col * (brickWidth + 1);  // X-position of the brick
                int y = row * (brickHeight + 1);  // Y-position of the brick with 1-pixel gap
                bricks.add(new Brick(x, y, brickWidth, brickHeight, rowColor));
            }
        }
        
        //add init ball
        balls.add(new Ball(350, 300, new Vector(1, 1)));
        //make the screen
        JFrame frame = new JFrame("Breakout in Java");
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(810,610);
        
        
        //Draw function
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                
                //clear screen
                g2d.setColor(Color.black);
                g2d.fillRect(0, 0, 800, 600);
                for(Brick b : bricks) {
                    b.drawBrick(g2d);
                }
                paddle.drawPaddle(g2d);
                
                for(Ball b : balls) {
                    b.drawBall(g2d);
                    bricks = b.updatePos(bricks, paddle);
                }
                for(Upgrade u : upgrades) { //TODO: have upgrades work ig
                    u.drawUpgrade(g2d);
                }
                
            }
        };
        
        
        
        //Get user input
        frame.addKeyListener(new KeyListener() {
            @Override //needs to override the keyTyped method to properly implement KeyListener
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightPressed = false;
                }
            }
        });
        
        frame.setContentPane(panel);
        frame.setVisible(true);
        while(true) { //game loop
            try {
                Thread.sleep(2); //prevent CPU useage from going to 100%!
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            //update paddle pos
            if (leftPressed) {
                paddle.setX(Math.max(paddle.getX() - 2, 0)); //prevent going off-screen
            }
            if (rightPressed) {
                paddle.setX(Math.min(paddle.getX() + 2, 800 - paddle.getWIDTH()));
            }
            
            panel.repaint();
        }
    }
    
}