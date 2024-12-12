/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.breakout;

import com.mycompany.breakout.upgrade.Upgrade;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author TMacRae2026
 */

/*
THE PLAN: 
Set up the game window:
    Initialize a JFrame screen
Create the paddle:
    Represent the paddle as a simple rectangle.
    Position it at the bottom of the screen.
    Allow the paddle to move horizontally using keyboard input (use keylistener).
Create the ball:
    Represent the ball as a small rectangle or circle.
    Implement ball movement with physics (use a vector w/ 2 doubles)
Create the bricks:
    Use a for loop to make a bunch of bricks
Implement collision detection:
    Check if the ball intersects with the paddle, bricks, or walls using bounding boxes
Track the score:
    Increase the score by 100 when the ball breaks a brick.
    Handle game over condition:
    Remove balls from list when they fall below the screen.
    If the ball list size = 0 game over
Implement the game loop:
    Continuously update the game state (move the ball, check collisions, and refresh the screen).
Upgrades:
    Make an upgrade object
    Have upgrades extend the class

*/
public class Breakout {
    
    public static Paddle paddle = new Paddle(350, 500);
    
    //init input variables
    static boolean leftPressed = false;
    static boolean rightPressed = false;
    
    
    static ArrayList<Brick> bricks = new ArrayList<>();
    public static ArrayList<Ball> balls = new ArrayList<>();
    public static ArrayList<Upgrade> upgrades = new ArrayList<>();
    
    public static int score = 0;
    
    
    
    static boolean shouldShowInstructions = true;
    
    public static void main(String[] args) {
        
        buildBricks(); //create the rows of bricks
        
        //add init ball
        balls.add(new Ball(200, 300, new Vector(1, 1)));
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
                
                GameStatus gameStatus = checkGameStatus();
                
                g2d.scale(3, 3); //scale up the graphics of the win and lose text
                
                
                if(gameStatus == GameStatus.WON) { // tell the user they won!
                    g2d.fillRect(0, 0, 800, 600);
                    for(Ball b : balls) {
                        b.drawBall(g2d);
                    }
                    g2d.setColor(Color.RED);
                    g2d.drawString("YOU WIN!!", 100, 100);
                    return;
                }
                
                if(gameStatus == GameStatus.LOST) { // tell the ueser they lost :(
                    g2d.setColor(Color.RED);
                    g2d.drawString("You Lost :( press space to restart", 50, 100);
                    return;
                }
                
                if(gameStatus == GameStatus.SHOWTEXT) { // give the user instruction
                    g2d.setColor(Color.RED);
                    g2d.drawString("Use the left and right keys to move the paddle.", 15, 50);
                    g2d.drawString("Use the paddle to bounce the balls.", 15, 65);
                    g2d.drawString("Use the paddle to catch the upgrades.", 15, 80);
                    g2d.drawString("Your goal is to break all the bricks.", 15, 95);
                    g2d.drawString("Press space to start!", 15, 110);
                    return;
                }
                
                g2d.scale(0.3333, 0.3333d); //reset the graphics scale to be 1/3 the scale of the text
                //remove any balls off screen
                Iterator<Ball> iterator = balls.iterator();
                while (iterator.hasNext()) {
                    Ball ball = iterator.next();
                    if (ball.getY() > 900) { // remove balls that are off screen
                        iterator.remove();
                    }
                }
                //clear screen
                g2d.setColor(Color.black);
                g2d.fillRect(0, 0, 800, 600); // clear the screen by putting a big FAT black square over everything
                for(Brick b : bricks) {
                    b.drawBrick(g2d);
                }
                paddle.drawPaddle(g2d); //draw the paddle
                
                g2d.drawString("Score: " + score, 10, 550);
                
                for(Ball b : balls) { // update all balls from the balls list
                    b.drawBall(g2d);
                    bricks = b.updatePos(bricks, paddle);
                }
                for(Upgrade u : upgrades) { // update all the upgrade objects
                    u.updatePos();
                    u.checkColliding(paddle);
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
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (checkGameStatus() == GameStatus.LOST) { // handle reseting the game
                        score = 0;
                        bricks.clear();
                        buildBricks();
                        balls.add(new Ball(200, 300, new Vector(1, 1)));
                    } else if (checkGameStatus() == GameStatus.SHOWTEXT) {
                        // Start the game by changing the state to PLAYING
                        shouldShowInstructions = false;
                        score = 0;
                        bricks.clear();
                        buildBricks();
                    }
                }

                
                if (e.getKeyCode() == KeyEvent.VK_LEFT) { // handle paddle movement
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
                Thread.sleep(3); //prevent CPU useage from going to 100%! (aswell as preventing the ball from moving super duper fast)
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
    
    
    public static GameStatus checkGameStatus() {
        if(bricks.size() <= 0) {
            return GameStatus.WON;
        }
        if(balls.size() <= 0) {
            return GameStatus.LOST;
        }
        if(shouldShowInstructions){
            return GameStatus.SHOWTEXT;
        }
        return GameStatus.PLAYING;
    }
    
    static void buildBricks() {
        int rows = 8;
        int cols = 10;
        int screenWidth = 800;
        int brickWidth = (screenWidth - (cols-1)) / cols ;
        int brickHeight = 20;
        
        
        
        Color[] rowColors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
            Color.CYAN, Color.MAGENTA, Color.PINK
        }; // list of colors that will be cycled through
        
        for (int row = 0; row < rows; row++) { // Loop through rows
            Color rowColor = rowColors[row % rowColors.length];
            
            for (int col = 0; col < cols; col++) { // Loop through columns
                int x = col * (brickWidth + 1);  // X-position of the brick
                int y = row * (brickHeight + 1);  // Y-position of the brick with 1-pixel gap
                bricks.add(new Brick(x, y, brickWidth, brickHeight, rowColor));
            }
        }
    }
    
}

enum GameStatus {
  SHOWTEXT,
  PLAYING,
  WON,
  LOST
}