/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import board.Board;
import Pieces.Piece;
import Pieces.Type;
import board.Position;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Nabil Amimer
 */
public class GameController {

    private Board board;

    //Board Pieces
    //black Pieces
    private Piece blackPawn1;
    private Piece blackPawn2;
    private Piece blackPawn3;
    private Piece blackPawn4;
    private Piece blackPawn5;
    private Piece blackPawn6;
    private Piece blackPawn7;
    private Piece blackPawn8;

    private Piece queenB;
    private Piece kingB;
    private Piece rookB1;
    private Piece rookB2;
    private Piece knightB1;
    private Piece knightB2;
    private Piece bishopB1;
    private Piece bishopB2;

    //white Pieces
    private Piece whitePawn1;
    private Piece whitePawn2;
    private Piece whitePawn3;
    private Piece whitePawn4;
    private Piece whitePawn5;
    private Piece whitePawn6;
    private Piece whitePawn7;
    private Piece whitePawn8;

    private Piece queenW;
    private Piece kingW;
    private Piece rookW1;
    private Piece rookW2;
    private Piece knightW1;
    private Piece knightW2;
    private Piece bishopW1;
    private Piece bishopW2;

    public GameController(Board board) {
        this.board = board;
        createBlackPieces();
        createWhitePieces();
    }
    
    //next two methods are only used to create and insert the pieces on the board
    
    private void createBlackPieces() {
        blackPawn1 = new Piece(1, 2, Type.PAWN, true, this.board);
        blackPawn2 = new Piece(2, 2, Type.PAWN, true, this.board);
        blackPawn3 = new Piece(3, 2, Type.PAWN, true, this.board);
        blackPawn4 = new Piece(4, 2, Type.PAWN, true, this.board);
        blackPawn5 = new Piece(5, 2, Type.PAWN, true, this.board);
        blackPawn6 = new Piece(6, 2, Type.PAWN, true, this.board);
        blackPawn7 = new Piece(7, 2, Type.PAWN, true, this.board);
        blackPawn8 = new Piece(8, 2, Type.PAWN, true, this.board);

        queenB = new Piece(4, 1, Type.QUEEN, true, board);
        rookB1 = new Piece(1, 1, Type.ROOK, true, board);
        rookB2 = new Piece(8, 1, Type.ROOK, true, board);
        bishopB1 = new Piece(3, 1, Type.BISHOP, true, board);
        bishopB2 = new Piece(6, 1, Type.BISHOP, true, board);
        knightB1 = new Piece(2, 1, Type.KNIGHT, true, board);
        knightB2 = new Piece(7, 1, Type.KNIGHT, true, board);
        kingB = new Piece(5, 1, Type.KING, true, board);
    }

    private void createWhitePieces() {
        whitePawn1 = new Piece(1, 7, Type.PAWN, false, this.board);
        whitePawn2 = new Piece(2, 7, Type.PAWN, false, this.board);
        whitePawn3 = new Piece(3, 7, Type.PAWN, false, this.board);
        whitePawn4 = new Piece(4, 7, Type.PAWN, false, this.board);
        whitePawn5 = new Piece(5, 7, Type.PAWN, false, this.board);
        whitePawn6 = new Piece(6, 7, Type.PAWN, false, this.board);
        whitePawn7 = new Piece(7, 7, Type.PAWN, false, this.board);
        whitePawn8 = new Piece(8, 7, Type.PAWN, false, this.board);

        queenW = new Piece(4, 8, Type.QUEEN, false, board);
        rookW1 = new Piece(1, 8, Type.ROOK, false, board);
        rookW2 = new Piece(8, 8, Type.ROOK, false, board);
        bishopW1 = new Piece(3, 8, Type.BISHOP, false, board);
        bishopW2 = new Piece(6, 8, Type.BISHOP, false, board);
        knightW1 = new Piece(2, 8, Type.KNIGHT, false, board);
        knightW2 = new Piece(7, 8, Type.KNIGHT, false, board);
        kingW = new Piece(5, 8, Type.KING, false, board);
    }
    
    // used to get what position the mouse has been clicked on and find if there is a piece on that board position
    private Position getClickPositionPosition(double x , double y){
        int counterX = 1;
        int counterY = 1;
        
        if(x < board.getChessBoardOffsetX() || x > board.getChessBoardOffsetX() + 800
                || y < board.getChessBoardOffsetY() || y > board.getChessBoardOffsetY() + 800)
        {
            Position pos = new Position(800, 800);
            System.out.println("Clicked Outside of the board");
            return pos;
        }
        
        for(int i = 1; i < 8; i++){
            if(x > board.getTileSize()*i + board.getChessBoardOffsetX()) counterX++;
            else break;
        }
        for(int i = 1; i < 8; i++){
            if(y > board.getTileSize()*i + board.getChessBoardOffsetY()) counterY++;
            else break;
        }
        Position pos = new Position(counterX, counterY);
        System.out.println(pos);
        return pos;
    }
    
    
    private void isPieceOnPosition(Position pos){
        for(Piece p : getAllPiecesOnBoard()){
            if(p.getPos().equals(pos)){
                System.out.println(p);
                break;
            }
        }
    }    
    
    private ArrayList<Piece> getAllPiecesOnBoard(){
        ArrayList<Piece> pieces = new ArrayList<>();
        
        for(Node n : board.getChildren()){
            if(n instanceof Piece){
                pieces.add((Piece) n);
            }
        }
        return pieces;
    }
            
    public MousePressedController getMouseController(){
        return new MousePressedController();
    }
    
    //creating classes that will handle the events of mouse pressing
    private class MousePressedController implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent e) {
            Position pos = getClickPositionPosition(e.getX(), e.getY());
            isPieceOnPosition(pos);
        }
        
    }
}
