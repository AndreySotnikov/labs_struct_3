/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author andrey
 */
public class MyBorder extends AbstractBorder{
    private final int thickness=2;
    private final int deletedborder;
    public MyBorder(int del) {
        this.deletedborder = del;
    }	 
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Stroke newStroke = new BasicStroke ( 2f );        
        g2d.setStroke (newStroke);
        if (deletedborder!=1) //up
            g2d.drawLine(x, y, x+width, y);
        if (deletedborder!=4) //left
            g2d.drawLine(x, y, x, y+height);
        if (deletedborder!=3) //down
            g2d.drawLine(x, y+height, x+width, y+height);
        if (deletedborder!=2) //right
            g2d.drawLine(x+width, y, x+width, y+height);
    }	 
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, thickness, thickness, thickness);
    }	 
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
       insets.left = insets.top = insets.right = insets.bottom = thickness;
       return insets;
    }    
}
