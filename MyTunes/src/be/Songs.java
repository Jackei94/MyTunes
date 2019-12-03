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
    public String songName;
    public String songArtist;
    public String category;
    public String filePath;
    public double time;
    private int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getSongName()
    {
        return songName;
    }

    public void setSongName(String songName)
    {
        this.songName = songName;
    }

    public String getSongArtist()
    {
        return songArtist;
    }

    public void setSongArtist(String songArtist)
    {
        this.songArtist = songArtist;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }
    
    public String toString()
    {
        return "Song{" + "songName=" + songName + ", songArtist=" + songArtist + ", category=" + category + ", filePath=" + filePath + ", time=" + time + ", id=" + id + '}';
    }
    
    
}