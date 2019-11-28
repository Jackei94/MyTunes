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
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MediaPlayer {
   public static void main(String[] args)
   {
      
   }
      public MediaPlayer(){

        try{

             FileInputStream fis = new FileInputStream("bensound-dubstep_1");
             Player playMP3 = new Player(fis);

             playMP3.play();

        }  catch(Exception e){
             System.out.println(e);
           }
    } 
}