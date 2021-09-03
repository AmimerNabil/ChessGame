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
import java.util.Objects;

/**
 *
 * @author Nabil Amimer
 */
public class Piece extends ImageView{
    
    private String path = "chessPieces/";
    private double posX ,posY;
    private Position ChessBoardPos;
    private Image image, imageAfter;
    private boolean firstMove;
    private Type type;
    
    private static int ID;
    private int id;
    //if false = white, if true = black
    private boolean team;
    private boolean isEaten;
    
    private boolean isInCheck;
        
    //Adjustments 
    private final int offsetKingQueenKnight = 9;
    
    public Piece(int posX, int posY, Type type, boolean team, Board board){
        //setting ID
        ID++;
        id = ID;    
        
        
        //SETTING UP THE PIECE AND ITS IMAGE
        this.type = type;
        this.team = team;
        path += type.toString().toLowerCase();
        if(team) path += "B.png";
        else path += "W.png";
        image = new Image(path);
        imageAfter = new Image(path, image.getWidth()*0.65, image.getHeight()*0.65, false, true);
        setImage(imageAfter);
        isInCheck = false;
        
        //creating an array for its position
        
        //chess Board position is the concrete position on the chess board
        // (1,2) or (5 , 4) for example
        ChessBoardPos = new Position(posX, posY);
        posX--; posY--;
        this.posX = board.getXBoardPosition(posX, posY);
        this.posY = board.getYBoardPosition(posX, posY);
        
        
        //defining offsets
        if(type == Type.QUEEN || type == Type.KING || type == Type.KNIGHT) this.posX -= offsetKingQueenKnight;
        setLayoutX(this.posX);
        setLayoutY(this.posY); 
        
        //in pane position is the concrete position relative to the Pane
        
        isEaten = false;
        //first move 
        firstMove = true;
        
        board.insertElement(this);
    }
    
    public void remove(Board board){
        board.removeElement(this);
    }
    
    @Override
    public String toString(){
        String color;
        if(team) color = "black";
        else color = "white";
        
        return color + " " + type.toString().toLowerCase() + " at position " + getPos() + ""
                + " with ID : " + id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.path);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.posX) ^ (Double.doubleToLongBits(this.posX) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.posY) ^ (Double.doubleToLongBits(this.posY) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.ChessBoardPos);
        hash = 89 * hash + Objects.hashCode(this.image);
        hash = 89 * hash + Objects.hashCode(this.imageAfter);
        hash = 89 * hash + (this.firstMove ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + this.id;
        hash = 89 * hash + (this.team ? 1 : 0);
        hash = 89 * hash + (this.isEaten ? 1 : 0);
        hash = 89 * hash + this.offsetKingQueenKnight;
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
        final Piece other = (Piece) obj;
        if (Double.doubleToLongBits(this.posX) != Double.doubleToLongBits(other.posX)) {
            return false;
        }
        if (Double.doubleToLongBits(this.posY) != Double.doubleToLongBits(other.posY)) {
            return false;
        }
        if (this.firstMove != other.firstMove) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.team != other.team) {
            return false;
        }
        if (this.isEaten != other.isEaten) {
            return false;
        }
        if (this.offsetKingQueenKnight != other.offsetKingQueenKnight) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.ChessBoardPos, other.ChessBoardPos)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.imageAfter, other.imageAfter)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    public boolean isIsInCheck() {
        return isInCheck;
    }

    public void setIsInCheck(boolean isInCheck) {
        this.isInCheck = isInCheck;
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

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Piece.ID = ID;
    }

    public int getPieceId() {
        return id;
    }

    public void setPieceId(int id) {
        this.id = id;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean getIsEaten() {
        return isEaten;
    }

    public void setIsEaten(boolean isEaten) {
        this.isEaten = isEaten;
    }
    
    
    
}
