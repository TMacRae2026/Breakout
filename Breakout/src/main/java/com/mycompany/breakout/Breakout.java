/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author TMacRae2026
 */
public class Breakout {
    
    public Breakout(){
        super();
    }
    
    static Ball ball = new Ball();
    static Brick testBrick = new Brick(100, 100, Color.BLUE);
    
    
    public static void main(String[] args) {
        //set init ball pos
        ball.setX(50);
        ball.setY(50);
        //make the screen
        JFrame frame = new JFrame("BasicJPanel");
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                
                //clear screen
                g2d.setColor(Color.black);
                g2d.fillRect(0, 0, 800, 600);
                testBrick.drawBrick(g2d);
                ball.drawBall(g2d);
                ball.updatePos();
            }
        };
        frame.setContentPane(panel);
        frame.setVisible(true);
        while(true) {
            try {
                Thread.sleep(1);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            panel.repaint();
        }
    }
    
    
}
