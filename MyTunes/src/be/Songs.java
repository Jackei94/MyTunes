/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tramm
 */
public class Songs
{
   private final SimpleIntegerProperty id;
   private final SimpleStringProperty songName;
   private final SimpleStringProperty songArtist;
   private final SimpleDoubleProperty time;
   private final SimpleStringProperty category;

   public Songs(int id, String songName, String songArtist, double time, String category)
   {
      this.id = new SimpleIntegerProperty(id);
      this.songName = new SimpleStringProperty(songName);
      this.songArtist = new SimpleStringProperty(songArtist);
      this.time = new SimpleDoubleProperty(time);
      this.category = new SimpleStringProperty (category);
   }

    public int getId()
    {
        return id.get();
    }

    public String getSongArtist()
    {
        return songArtist.get();
    }

    public String getSongName()
    {
        return songName.get();
    }

    public double getTime()
    {
        return time.get();
    }

    public String getCategory()
    {
        return category.get();
    }
    
//    @Override
//    public String toString()
//    {
//        return songName + " - " + songArtist + " - " + category + " - " + time;
//    }
}