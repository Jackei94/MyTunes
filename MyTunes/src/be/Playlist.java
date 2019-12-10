/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tramm
 */
public class Playlist
{
    public String plName;
    public int plSongs;
    public int plTime;
    private int id;
private final List<Songs> songsList;

   public Playlist()
   {
    
        this.songsList = new ArrayList();
   }
    public String getPlName()
    {
        return plName;
    }

    public void setPlName(String plName)
    {
        this.plName = plName;
    }

    public int getPlSongs()
    {
        return plSongs;
    }

    public void setPlSongs(int plSongs)
    {
        this.plSongs = plSongs;
    }

    public int getPlTime()
    {
        return plTime;
    }

    public void setPlTime(int plTime)
    {
        this.plTime = plTime;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    /*
     * returns songList
     */
    public List<Songs> getSongList() {
        return songsList;
    }
}
