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
import board.SpeicialRectangle;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    //moving Variables : reinitialized every time the mouse is clicked
    private double initialX;
    private double initialY;
    private Piece movingPiece;
    private double initialTranslateX;
    private double initialTranslateY;

    private Position initialPosition;
    private int indexOfSquare;
    private Color colorBefore;
    ArrayList<Position> positions;

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

    //used to get the index of a square at a position x and y on the board
    private int getIndexOfSquare(double boardX, double boardY) {
        return ((int) boardX - 1) * 8 + (int) boardY - 1;
    }

    // used to get what position the mouse has been clicked on and find if there is a piece on that board position
    private Position getClickPositionPosition(double x, double y) {
        int counterX = 1;
        int counterY = 1;

        if (x < board.getChessBoardOffsetX() || x > board.getChessBoardOffsetX() + 800
                || y < board.getChessBoardOffsetY() || y > board.getChessBoardOffsetY() + 800) {
            Position pos = new Position(1000, 1000);
            System.out.println("Clicked Outside of the board");
            return pos;
        }

        for (int i = 1; i < 8; i++) {
            if (x > board.getTileSize() * i + board.getChessBoardOffsetX()) {
                counterX++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (y > board.getTileSize() * i + board.getChessBoardOffsetY()) {
                counterY++;
            } else {
                break;
            }
        }
        Position pos = new Position(counterX, counterY);
        return pos;
    }

    private Piece isPieceOnPosition(Position pos) {
        for (Piece p : getAllPiecesOnBoard()) {
            if (p.getPos().equals(pos)) {
                return p;
            }
        }
        return null;
    }

    private ArrayList<Piece> getAllPiecesOnBoard() {
        ArrayList<Piece> pieces = new ArrayList<>();

        for (Node n : board.getChildren()) {
            if (n instanceof Piece) {
                pieces.add((Piece) n);
            }
        }
        return pieces;
    }
    
    private void resetColors(){
        for(SpeicialRectangle r: board.getSquares()){
            r.setFill(r.getInitalColor());
        }
    }

    public MousePressedController getMouseController() {
        return new MousePressedController();
    }

    //creating classes/ lambda methods that will handle the events of mouse pressing
    private class MousePressedController implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            initialPosition = getClickPositionPosition(e.getX(), e.getY());
            if (isPieceOnPosition(initialPosition) != null) {
                indexOfSquare = getIndexOfSquare(initialPosition.getXpos(), initialPosition.getYpos());
                colorBefore = (Color) board.getSquares().get(indexOfSquare).getFill();
                board.getSquares().get(indexOfSquare).setFill(Color.GOLD);
                movingPiece = isPieceOnPosition(initialPosition);

                initialX = movingPiece.getPosX() + 25;
                initialY = movingPiece.getPosY() + 29;

                initialTranslateX = 0;
                initialTranslateY = 0;
            } else {
                movingPiece = null;
            }
        }
    }

    public void dragEvent(MouseEvent e) {
        if (movingPiece != null) {
            double offsetX = e.getSceneX() - initialX;
            double offsetY = e.getSceneY() - initialY;
            double newTranslateX = initialTranslateX + offsetX;
            double newTranslateY = initialTranslateY + offsetY;

            handleMovement(movingPiece);

            movingPiece.setTranslateX(newTranslateX);
            movingPiece.setTranslateY(newTranslateY);
        }
    }

    public void dragReleased(MouseEvent e) {
        boolean correctMove = false;
        double posX = 0;
        double posY = 0;
        if (movingPiece != null) {
            Position pos = getClickPositionPosition(e.getX(), e.getY());

            if (pos.getXpos() != 1000) {
                board.getSquares().get(indexOfSquare).setFill(colorBefore);
                posX = board.getXBoardPosition((int) pos.getXpos() - 1, (int) pos.getYpos() - 1);
                posY = board.getYBoardPosition((int) pos.getXpos() - 1, (int) pos.getYpos() - 1);

                for (Position p : positions) {
                    if (p.equals(pos)) {
                        correctMove = true;
                    }
                }
            }

            movingPiece.setTranslateX(0);
            movingPiece.setTranslateY(0);
            if (correctMove) {
                movingPiece.setPos(pos);
                movingPiece.setPosX((int) posX);
                movingPiece.setPosY((int) posY);
                movingPiece.setLayoutX(posX);
                movingPiece.setLayoutY(posY);
                movingPiece.setFirstMove(false);
            } else {
                movingPiece.setPosX((int) initialX - 25);
                movingPiece.setPosY((int) initialY - 29);
                movingPiece.setLayoutX((int) initialX - 25);
                movingPiece.setLayoutY((int) initialY - 29);
            }
            
            //resetColors
            resetColors();
        }
    }

    private void handleMovement(Piece piece) {
        positions = new ArrayList<>();

        int index;
        switch (piece.getType()) {
            case PAWN:
                positions = pawnMoves(piece);
                break;
            case ROOK:
                break;
            case KNIGHT:
                break;
            case BISHOP:
                break;
            case QUEEN:
                break;
            case KING:
                break;
        }

        for (Position p : positions) {
            index = getIndexOfSquare(p.getXpos(), p.getYpos());
            board.getSquares().get(index).setFill(Color.GREY);
        }
    }

    private ArrayList<Position> pawnMoves(Piece p) {
        Position pos = p.getPos();
        ArrayList<Position> possiblePos = new ArrayList<>();

        if (p.isTeam()) {
            possiblePos.add(new Position(pos.getXpos(), pos.getYpos() + 1));
            if (p.isFirstMove()) {
                possiblePos.add(new Position(pos.getXpos(), pos.getYpos() + 2));
            }
        } else {
            possiblePos.add(new Position(pos.getXpos(), pos.getYpos() - 1));
            if (p.isFirstMove()) {
                possiblePos.add(new Position(pos.getXpos(), pos.getYpos() - 2));
            }
        }
        return possiblePos;
    }

    
    //class ends
}
