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
    int width, height;
    Color c;
    
    public Brick(int x, int y, int width, int height, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.width = width;
        this.height = height;
    }
    
    public void drawBrick(Graphics2D g2d) {
        g2d.setColor(c);
        g2d.fillRect(x, y, width, height);
    }
    
    public int checkColliding(Ball ball) {
        // Adjust the ball's position and size
        int ballRadius = 10;
        int ballLeft = (int) ball.x - ballRadius;
        int ballRight = (int) ball.x + ballRadius;
        int ballTop = (int) ball.y - ballRadius;
        int ballBottom = (int) ball.y + ballRadius;

        // Check if the ball's bounding box overlaps with the brick's bounding box
        int brickLeft = this.x;
        int brickRight = this.x + width;
        int brickTop = this.y;
        int brickBottom = this.y + height;

        // check if ball colliding on sides
        boolean collisionLeftOrRight = (ballRight > brickLeft && ballLeft < brickRight) &&
                                        (ball.y >= brickTop && ball.y <= brickBottom);
        boolean collisionTopOrBottom = (ballBottom > brickTop && ballTop < brickBottom) &&
                                        (ball.x >= brickLeft && ball.x <= brickRight);
        
        
        //Return 1 if the collision was on the x axis, 2 if the collision was on the y axis, and 0 for no colision
        if(collisionLeftOrRight) {
            return 1;
        }
        if(collisionTopOrBottom) {
            return 2;
        }
        return 0;
    }
}