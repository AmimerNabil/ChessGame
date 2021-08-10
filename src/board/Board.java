/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Nabil Amimer
 */
public class Board extends Pane{

    //actual chess board
    private ArrayList<SpeicialRectangle> squares;
    private int tiles;
    private int tileSize;
    private int BoardSize;
    
    private Pane chessPane;
    private int chessBoardOffsetX = 20;
    private int chessBoardOffsetY = 40;
    private Position[][] positions = new Position[8][8];
    
    //extra space for moves and info
    private int moveZoneWidth = 300;
    private int piecesTakenHeight = 100;
    
    
    public Board(){
        //sizing of the board and parameters
        tiles = 8; tileSize = 100; BoardSize = tiles*tileSize;
        setPrefSize(BoardSize + moveZoneWidth, BoardSize + piecesTakenHeight);
        
        //chessPane
        squares = new ArrayList<>();
        chessPane = new Pane();
        chessPane.setPrefSize(BoardSize, BoardSize);
        chessPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        chessPane.setLayoutX(chessBoardOffsetX);
        chessPane.setLayoutY(chessBoardOffsetY);
        
        getChildren().add(chessPane);
        setChessBoardLines();
    }
    
    
    private void setChessBoardLines(){
        int counter = 1;
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                SpeicialRectangle rec = new SpeicialRectangle(i*tileSize + chessBoardOffsetX, j*tileSize + chessBoardOffsetY, tileSize, tileSize);
                positions[i][j] = new Position(i*tileSize + chessBoardOffsetX, j*tileSize + chessBoardOffsetY);
                rec.setStroke(Color.BLACK);
                if(counter %2 == 0) {
                    rec.setFill(Color.CHOCOLATE);
                    rec.setInitalColor(Color.CHOCOLATE);
                }
                else{
                    rec.setFill(Color.ANTIQUEWHITE);
                    rec.setInitalColor(Color.ANTIQUEWHITE);
                }
                squares.add(rec);
                counter++;
            }
           counter++;
        }
        
        for(Rectangle r: squares){
            getChildren().add(r);
        }
    }
    
    public double getXBoardPosition(int a , int b){
        return positions[a][b].getXpos() + 15 ;
    }
    
    public double getYBoardPosition(int a , int b){
        return positions[a][b].getYpos() + 10;
    }
    
    public void insertElement(Node n){
        getChildren().add(n);
    }
    
    public void removeElement(Node n){
        getChildren().remove(n);
    }
    public ArrayList<SpeicialRectangle> getSquares() {
        return squares;
    }

    public void setSquares(ArrayList<SpeicialRectangle> squares) {
        this.squares = squares;
    }

    public int getTiles() {
        return tiles;
    }

    public void setTiles(int tiles) {
        this.tiles = tiles;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getBoardSize() {
        return BoardSize;
    }

    public void setBoardSize(int BoardSize) {
        this.BoardSize = BoardSize;
    }

    public Pane getChessPane() {
        return chessPane;
    }

    public void setChessPane(Pane chessPane) {
        this.chessPane = chessPane;
    }

    public int getChessBoardOffsetX() {
        return chessBoardOffsetX;
    }

    public void setChessBoardOffsetX(int chessBoardOffsetX) {
        this.chessBoardOffsetX = chessBoardOffsetX;
    }

    public int getChessBoardOffsetY() {
        return chessBoardOffsetY;
    }

    public void setChessBoardOffsetY(int chessBoardOffsetY) {
        this.chessBoardOffsetY = chessBoardOffsetY;
    }

    public int getMoveZoneWidth() {
        return moveZoneWidth;
    }

    public void setMoveZoneWidth(int moveZoneWidth) {
        this.moveZoneWidth = moveZoneWidth;
    }

    public int getPiecesTakenHeight() {
        return piecesTakenHeight;
    }

    public void setPiecesTakenHeight(int piecesTakenHeight) {
        this.piecesTakenHeight = piecesTakenHeight;
    }
    
    
    
}
