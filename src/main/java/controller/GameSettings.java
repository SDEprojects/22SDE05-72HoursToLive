package main.java.controller;

import main.java.model.RoomMovement;
import main.java.view.Story;

import java.io.IOException;
import java.util.ResourceBundle;

public class GameSettings {
    public static String roomName;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    public void readGameStory() {
        Story gameStory = new Story();
        gameStory.titleScreen();
        gameStory.selectDifficulty();
        gameStory.introText();
    }
    public void startGame() throws IOException {
        RoomMovement movement = new RoomMovement();
        GameController gameController = new GameController();
        movement.firstRoom();
        while (true) {
            if (GameController.player.getHealth() <= 0) {
                System.out.println(bundle.getString("player_dead1"));
                break;
            }
            else if (GameController.timer== 24){
                System.out.println(bundle.getString("time_out1"));
                break;
            }
            else if (GameController.player.getInventory().contains("Trophy")) {
                System.out.println(bundle.getString("trophy_response1"));
                break;
            }
            else {
                gameController.userChoice();
            }
        }


    }
    public void endGame() {
        System.out.println(bundle.getString("game_over1"));
        System.out.println(bundle.getString("game_over2"));
        System.out.println(bundle.getString("game_over3"));
    }
}
