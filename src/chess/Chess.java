/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;
import board.Board;
import javafx.scene.Scene;
import GameController.GameController;
import javafx.scene.Parent;

/**
 *
 * @author Nabil Amimer
 */
public class Chess extends Application{
    
    Board board;
    GameController controller;
    
    private Parent createBoard(){
        board = new Board();
        controller = new GameController(board);
        return board;
    }

    @Override
    public void start(Stage stage)throws FileNotFoundException{
       
        Scene boardScene = new Scene(createBoard());
        boardScene.setOnMousePressed(controller.getMouseController());
        boardScene.setOnMouseDragged(event -> controller.dragEvent(event));
        boardScene.setOnMouseReleased(event -> controller.dragReleased(event));
        stage.setScene(boardScene);
        stage.setResizable(false);
        stage.setTitle("Chess");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
}
