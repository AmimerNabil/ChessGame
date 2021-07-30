/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import board.Board;
import board.Position;

/**
 *
 * @author Nabil Amimer
 */
public class Piece extends ImageView{
    
    private String path = "chessPieces/";
    private double posX ,posY;
    private Position ChessBoardPos;
    private Image image, imageAfter;
    private Type type;
    
    //if false = white, if true = black
    private boolean team;
    
    //Adjustments 
    private int offsetKingQueenKnight = 9;
    
    public Piece(int posX, int posY, Type type, boolean team, Board board){
        //SETTING UP THE PIECE AND ITS IMAGE
        this.type = type;
        this.team = team;
        path += type.toString().toLowerCase();
        if(team) path += "B.png";
        else path += "W.png";
        image = new Image(path);
        imageAfter = new Image(path, image.getWidth()*0.65, image.getHeight()*0.65, false, true);
        setImage(imageAfter);
        
        //creating an array for its position
        ChessBoardPos = new Position(posX, posY);
        
        //SETTING UP THE POSITION OF THE IMAGE
        //POSITION ENTERED IS POS ON BOARD, NOT ON SCREEN
        posX--;
        posY--;
        this.posX = board.getXBoardPosition(posX, posY);
        this.posY = board.getYBoardPosition(posX, posY);
        
        //defining offsets
        if(type == Type.QUEEN || type == Type.KING || type == Type.KNIGHT) this.posX -= offsetKingQueenKnight;
        setLayoutX(this.posX);
        setLayoutY(this.posY);
        board.insertElement(this);
    }
    
    @Override
    public String toString(){
        String color;
        if(team) color = "black";
        else color = "white";
        
        return color + " " + type.toString().toLowerCase() + " at position " + getPos();
    }
   
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Image getIniitialImage() {
        return image;
    }

    public void setInitialImage(Image image) {
        this.image = image;
    }

    public Image getImageAfter() {
        return imageAfter;
    }

    public void setImageAfter(Image imageAfter) {
        this.imageAfter = imageAfter;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isTeam() {
        return team;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public Position getPos() {
        return ChessBoardPos;
    }

    public void setPos(Position pos) {
        this.ChessBoardPos = pos;
    }
    
    
    
}
