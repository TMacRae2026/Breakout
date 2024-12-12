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
import java.util.ArrayList;

/**
 *
 * @author tyler
 */
public class Multiply extends Upgrade{
    
    
    public Multiply(double x, double y) {
        super(x, y, Color.green);
    }
    
    @Override
    public void applyUpgrade() {
        
        ArrayList<Ball> ballsCopy = new ArrayList<>(Breakout.balls);  // Create a copy of the list
        for (Ball b : ballsCopy) {
            Breakout.balls.add(new Ball(b.getX(), b.getY(), new Vector(-b.getDir().getX(), b.getDir().getY())));
        }

        
    }
    
    
    
}