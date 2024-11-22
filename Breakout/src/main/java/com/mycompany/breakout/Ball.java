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
public class Ball {
    
    int x, y;
    int size = 20;
    
    boolean dirX = true;
    boolean dirY = true;
    
    public Ball() {
        
    }
    
    public void drawBall(Graphics2D g2d) {
        g2d.setColor(Color.red);
        g2d.fillOval(x, y, size, size);
    }
    
    public void updatePos() {
        if(x>=800 - size) {
            dirX = false;
        }
        if(x<=0) {
            dirX = true;
        }
        if(dirX) {
            x++;
        } else{
            x--;
        }
        if(y>=600 - size) {
            dirY = false;
        }
        if(y<=0) {
            dirY = true;
        }
        if(dirY) {
            y++;
        } else {
            y--;
        }
        
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
