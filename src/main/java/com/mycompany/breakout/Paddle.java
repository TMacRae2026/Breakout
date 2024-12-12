/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.breakout;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author tyler
 */
public class Paddle {
    
    double x, y;
    final int WIDTH = 100;
    
    public Paddle(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void drawPaddle(Graphics2D g2d) { //pass the graphics object
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) x, (int) y, WIDTH, 10);
    }
    
    //basic getters and setters for paddle class
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWIDTH() {
        return WIDTH;
    }
    
    
    
}