/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.breakout.upgrade;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author tyler
 */
public class Upgrade {
    
    double x, y;
    Color c;

    public Upgrade(double x, double y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
    
    public void applyUpgrade() {} //Method will be overridden by child classes
    
    public void drawUpgrade(Graphics2D g2d) {
        g2d.setColor(c);
        g2d.fillOval((int) x, (int) y, 30, 30);
    }
    
    public void updatePos() {
        y--;
    }
    
}
