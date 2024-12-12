/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.breakout.upgrade.impl;

import com.mycompany.breakout.Ball;
import com.mycompany.breakout.Breakout;
import com.mycompany.breakout.Vector;
import com.mycompany.breakout.upgrade.Upgrade;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author TMacRae2026
 */
public class Add extends Upgrade {
    
    public Add(double x, double y) {
        super(x, y, Color.PINK);
    }
    
    @Override
    public void applyUpgrade() {
        Random rand = new Random(); // add a new ball at the paddle with a random x velocity
        Breakout.balls.add(new Ball(Breakout.paddle.getX() + 50, Breakout.paddle.getY() - 30, new Vector(rand.nextDouble(-1, 1), -1)));
    }
    
}
