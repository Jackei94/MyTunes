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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;




public class MediaPlay {
    
    private MediaPlayer mediaPlay;
    
    
    
   public static void main(String[] args)
   {
    MediaPlay mediaPlay = new MediaPlay();
    mediaPlay.playMedia();
    
   }
   public void playMedia() { 
   String bip = "file:/C:/Users/renej/Documents/GitHub/MyTunes/MyTunes/music/song1.mp3";
   Media hit = new Media(bip);
   mediaPlay = new MediaPlayer(hit);
   mediaPlay.play();
           }
   
}