/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

/**
 *
 * @author Nabil Amimer
 */
public class Position {
    
    private double[] boardPosition;
    private double xpos;
    private double ypos;
    
    
    
    public Position(double x , double y){
        boardPosition = new double[2];
        boardPosition[0] = x;
        boardPosition[1] = y;
        xpos = x;
        ypos = y;
    }
    
    @Override
    public String toString(){
        return "x : " + getXpos() + " / y : " + getYpos();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.xpos) ^ (Double.doubleToLongBits(this.xpos) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.ypos) ^ (Double.doubleToLongBits(this.ypos) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (Double.doubleToLongBits(this.xpos) != Double.doubleToLongBits(other.xpos)) {
            return false;
        }
        if (Double.doubleToLongBits(this.ypos) != Double.doubleToLongBits(other.ypos)) {
            return false;
        }
        return true;
    }

    
    
    public double[] getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(double[] boardPosition) {
        this.boardPosition = boardPosition;
    }

    public double getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
    
    
    
}
