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

/**
 *
 * @author tyler
 */
public class Multiply extends Upgrade{
    
    
    public Multiply(double x, double y) {
        super(x, y, Color.white);
    }
    
    @Override
    public void applyUpgrade() {
        
        for(Ball b : Breakout.balls) { // creates a new ball at every ball (with an opposite direction on the x-axis)
            Breakout.balls.add(new Ball(b.getX(), b.getY(), new Vector(-b.getDir().getX(), b.getDir().getY())));
        }
        
    }
    
}
