/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Nabil Amimer
 */
public class SpeicialRectangle extends Rectangle{
    private Color initalColor;
    
    public SpeicialRectangle( double d1 , double d2 , double d3, double d4){
        super(d1, d2, d3, d4);
        initalColor = Color.CHOCOLATE;
    }

    public Color getInitalColor() {
        return initalColor;
    }

    public void setInitalColor(Color initalColor) {
        this.initalColor = initalColor;
    }
    
}
