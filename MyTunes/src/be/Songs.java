/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

/**
 *
 * @author Tramm
 */
public class Songs
{
   private int id;
   private String songName;
   private String songArtist;
   private double time;
   private String category;

   public Songs(int id, String songName, String songArtist, double time, String category)
   {
      this.id = id;
      this.songName = songName;
      this.songArtist = songArtist;
      this.time = time;
      this.category = category;
   }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSongArtist()
    {
        return songArtist;
    }

    public void setSongArtist(String songArtist)
    {
        this.songArtist = songArtist;
    }

    public String getSongName()
    {
        return songName;
    }

    public void setSongName(String songName)
    {
        this.songName = songName;
    }

    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
    
    @Override
    public String toString()
    {
        return songName + " - " + songArtist + " - " + category + " - " + time;
    }
}