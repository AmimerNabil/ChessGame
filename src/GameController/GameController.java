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
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Nabil Amimer
 */
public class GameController {

    Board board;

    //Board Pieces
    //black Pieces
    private Piece blackPawn1,
            blackPawn2,
            blackPawn3,
            blackPawn4,
            blackPawn5,
            blackPawn6,
            blackPawn7,
            blackPawn8;

    private Piece queenB,
            kingB,
            rookB1,
            rookB2,
            knightB1,
            knightB2,
            bishopB1,
            bishopB2;

    //white Pieces
    private Piece whitePawn1,
            whitePawn2,
            whitePawn3,
            whitePawn4,
            whitePawn5,
            whitePawn6,
            whitePawn7,
            whitePawn8;

    private Piece queenW,
            kingW,
            rookW1,
            rookW2,
            knightW1,
            knightW2,
            bishopW1,
            bishopW2;

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

    private boolean turn;

    AnimationTimer timer;

    public GameController(Board board) {
        this.board = board;
        createBlackPieces();
        createWhitePieces();
        turn = false;

        timer = new AnimationTimer() {
            double old = -1;
            double elapsedTime = 0;
            double timeBefore;

            @Override
            public void handle(long now) {
                if (old < 0) {
                    old = now;
                }
                double delta = (now - old) / 1e9;
                timeBefore = elapsedTime;
                update(elapsedTime);
                old = now;
                elapsedTime += delta;
            }
        };
        timer.start();
    }

    private void update(double timeElapsed) {

        try {
            removeDeadElements();
        } catch (Exception e) {
            System.out.println("A piece has been eatten");
        }

    }

    //next two methods are only used to create and insert the pieces on the board
    private void createBlackPieces() {
        blackPawn1 = new Piece(1, 2, Type.PAWN, true, board);
        blackPawn2 = new Piece(2, 2, Type.PAWN, true, board);
        blackPawn3 = new Piece(3, 2, Type.PAWN, true, board);
        blackPawn4 = new Piece(4, 2, Type.PAWN, true, board);
        blackPawn5 = new Piece(5, 2, Type.PAWN, true, board);
        blackPawn6 = new Piece(6, 2, Type.PAWN, true, board);
        blackPawn7 = new Piece(7, 2, Type.PAWN, true, board);
        blackPawn8 = new Piece(8, 2, Type.PAWN, true, board);

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
        whitePawn1 = new Piece(1, 7, Type.PAWN, false, board);
        whitePawn2 = new Piece(2, 7, Type.PAWN, false, board);
        whitePawn3 = new Piece(3, 7, Type.PAWN, false, board);
        whitePawn4 = new Piece(4, 7, Type.PAWN, false, board);
        whitePawn5 = new Piece(5, 7, Type.PAWN, false, board);
        whitePawn6 = new Piece(6, 7, Type.PAWN, false, board);
        whitePawn7 = new Piece(7, 7, Type.PAWN, false, board);
        whitePawn8 = new Piece(8, 7, Type.PAWN, false, board);

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

    private ArrayList<Piece> getAllWhitePieces() {
        ArrayList<Piece> whitePieces = new ArrayList<>();

        for (Piece p : getAllPiecesOnBoard()) {
            if (!p.isTeam()) {
                whitePieces.add(p);
            }
        }

        return whitePieces;
    }

    private ArrayList<Piece> getAllBlackPieces() {
        ArrayList<Piece> blackPieces = new ArrayList<>();

        for (Piece p : getAllPiecesOnBoard()) {
            if (p.isTeam()) {
                blackPieces.add(p);
            }
        }

        return blackPieces;
    }

    private void resetColors() {
        for (SpeicialRectangle r : board.getSquares()) {
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
                movingPiece = isPieceOnPosition(initialPosition);
                if (movingPiece.isTeam() == turn) {
                    indexOfSquare = getIndexOfSquare(initialPosition.getXpos(), initialPosition.getYpos());
                    colorBefore = (Color) board.getSquares().get(indexOfSquare).getFill();
                    board.getSquares().get(indexOfSquare).setFill(Color.GOLD);

                    handleMovement(movingPiece);
                    initialX = movingPiece.getPosX() + 25;
                    initialY = movingPiece.getPosY() + 29;

                    initialTranslateX = 0;
                    initialTranslateY = 0;
                }

            } else {
                movingPiece = null;
            }
        }
    }

    public void dragEvent(MouseEvent e) {
        if (movingPiece != null && movingPiece.isTeam() == turn) {
            double offsetX = e.getSceneX() - initialX;
            double offsetY = e.getSceneY() - initialY;
            double newTranslateX = initialTranslateX + offsetX;
            double newTranslateY = initialTranslateY + offsetY;

            movingPiece.setTranslateX(newTranslateX);
            movingPiece.setTranslateY(newTranslateY);
        }
    }

    public void dragReleased(MouseEvent e) {
        boolean correctMove = false;
        double posX = 0;
        double posY = 0;
        if (movingPiece != null && movingPiece.isTeam() == turn) {
            Position pos = getClickPositionPosition(e.getX(), e.getY());

            if (pos.getXpos() != 1000) {
                board.getSquares().get(indexOfSquare).setFill(colorBefore);
                posX = board.getXBoardPosition((int) pos.getXpos() - 1, (int) pos.getYpos() - 1);
                posY = board.getYBoardPosition((int) pos.getXpos() - 1, (int) pos.getYpos() - 1);

                for (Position p : positions) {
                    //this is to figure out if we have left the piece in a possible possition
                    if (p.equals(pos)) {
                        correctMove = true;
                        //this is to handle what happens if there is a piece at this position. 
                        if (isPieceOnPosition(pos) != null) {
//                            if (isPieceOnPosition(pos).isTeam() == movingPiece.isTeam()) {
//                                correctMove = false;
//                            } else {
                                isPieceOnPosition(pos).setIsEaten(true);
                                isPieceOnPosition(pos).setVisible(false);
//                            }
                        }
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
                turn = !turn;

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
        Position front = new Position(0, 0);
        Position leftDiagonal = new Position(0, 0);
        Position rightDiagonal = new Position(0, 0);

        ArrayList<Position> possiblePos = new ArrayList<>();

        if (p.isTeam()) {
            front.setXpos((int) pos.getXpos());
            front.setYpos((int) pos.getYpos() + 1);

            leftDiagonal.setXpos((int) pos.getXpos() - 1);
            leftDiagonal.setYpos((int) pos.getYpos() + 1);

            rightDiagonal.setXpos((int) pos.getXpos() + 1);
            rightDiagonal.setYpos((int) pos.getYpos() + 1);

            possiblePos.add(front);
            if (p.isFirstMove()) {
                possiblePos.add(new Position(pos.getXpos(), pos.getYpos() + 2));
            }

            for (Piece enemyPieces : getAllWhitePieces()) {
                if (enemyPieces.getPos().equals(leftDiagonal) || enemyPieces.getPos().equals(rightDiagonal)) {
                    possiblePos.add(enemyPieces.getPos());
                }
            }

        } else {
            front.setXpos((int) pos.getXpos());
            front.setYpos((int) pos.getYpos() - 1);

            leftDiagonal.setXpos((int) pos.getXpos() - 1);
            leftDiagonal.setYpos((int) pos.getYpos() - 1);

            rightDiagonal.setXpos((int) pos.getXpos() + 1);
            rightDiagonal.setYpos((int) pos.getYpos() - 1);

            possiblePos.add(front);
            if (p.isFirstMove()) {
                possiblePos.add(new Position(pos.getXpos(), pos.getYpos() - 2));
            }

            for (Piece enemyPieces : getAllBlackPieces()) {
                if (enemyPieces.getPos().equals(leftDiagonal) || enemyPieces.getPos().equals(rightDiagonal)) {
                    possiblePos.add(enemyPieces.getPos());
                }
            }
        }

        if (isPieceOnPosition(front) != null) {
            possiblePos.remove(front);
        }
        return possiblePos;
    }

    private void removeDeadElements() throws IllegalArgumentException {
        for (Piece p : getAllPiecesOnBoard()) {
            if (p.getIsEaten()) {
                p.remove(board);
            }
        }
    }

    //class ends
}
