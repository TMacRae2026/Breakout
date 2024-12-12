/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.breakout.upgrade;

import com.mycompany.breakout.Paddle;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author tyler
 */
public class Upgrade {
    
    double x, y;
    Color c;
    
    boolean collected = false;
    
    public Upgrade(double x, double y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
    
    public void applyUpgrade() {} //Method will be overridden by classes that extend upgrade using @Override annotation
    
    public void drawUpgrade(Graphics2D g2d) {
        if(!collected) {
            g2d.setColor(c);
            g2d.fillOval((int) x, (int) y, 30, 30);
        }
    }
    
    public void updatePos() { // moves down half as fast as the balls
        y += 0.5;
    }
    
    public void checkColliding(Paddle paddle) {
        if(y == 500 - 30 && this.x >= paddle.getX() && this.x <= paddle.getX() + 100 && !collected) { //check the boudning box of the paddle against the current position
            
            this.applyUpgrade();
            collected = true;
            
        }
        
    }
    
}