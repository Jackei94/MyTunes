/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

/**
 *
 * @author Tramm
 */
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayer {
    public static void main(String[] args) {
        new JFXPanel();//Required to initialize JavaFX or I get this exception: Exception in thread "Thread-0" java.lang.IllegalStateException: Toolkit not initialized
        String fileLocation = "bensound-dubstep_1.mp3";
        System.out.println(fileLocation);
        Media hit = new Media(fileLocation);
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
}