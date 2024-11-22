/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.breakout;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author TMacRae2026
 */
public class Brick {
    
    int x, y;
    Color c;
    
    public Brick(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
    
    public void drawBrick(Graphics2D g2d) {
        g2d.setColor(c);
        g2d.fillRect(x, y, 100, 30);
    }
    
    
    
}
