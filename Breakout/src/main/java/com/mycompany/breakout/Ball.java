/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author TMacRae2026
 */
public class Ball {
    
    double x, y;
    int size = 20;
    
    Vector dir = new Vector(1, 1);
    
    public Ball(double x, double y, Vector dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    } 
    
    public Vector getDir() {
        return dir;
    }
    
    public void drawBall(Graphics2D g2d) {
        g2d.setColor(Color.red);
        g2d.fillOval((int) x, (int) y, size, size);
    }
    
    public ArrayList<Brick> updatePos(ArrayList<Brick> bricks, Paddle paddle) { // pretty much all game logic will originate from in here
        
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick b = iterator.next();
            int collide = b.checkColliding(this);
            switch (collide) {
                case 1 -> {
                    iterator.remove(); // Safely remove the brick
                    dir.setX(-dir.getX());
                }
                case 2 -> {
                    iterator.remove(); // Safely remove the brick
                    dir.setY(-dir.getY());
                }
                default -> {
                    // No collision
                }
            }
        }
        
        if(x>=800 - size) {
            dir.setX(-dir.getX());
        }
        if(x<=0) {
            dir.setX(-dir.getX());
        }
        
        if(y<=600 - 2 * size) {
            
        }
        if (y == 500 - size && this.x >= paddle.x && this.x <= paddle.x + 100) {
            dir.setY(-dir.getY());
            
            double paddleCenter = paddle.x + (paddle.getWIDTH() / 2);
            double hitPosition = this.x - paddleCenter; //distance from paddle center
            dir.setX(hitPosition / (paddle.getWIDTH() / 2)); //scale to a range [-1, 1]
        }
        
        if(y<=0) {
            dir.setY(-dir.getY());
        }
        
        x += dir.getX();
        y += dir.getY();
        
        
        return  bricks;
    }
    
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}